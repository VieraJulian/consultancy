package com.consultancy.reservations.application;

import com.consultancy.reservations.application.exception.InvalidStateException;
import com.consultancy.reservations.application.exception.ReservationNotFoundException;
import com.consultancy.reservations.application.exception.UserNotFoundException;
import com.consultancy.reservations.application.mapper.IReservationMapper;
import com.consultancy.reservations.domain.Reservation;
import com.consultancy.reservations.domain.ReservationState;
import com.consultancy.reservations.domain.dto.ProfessionalDTO;
import com.consultancy.reservations.domain.dto.ReservationRequestDTO;
import com.consultancy.reservations.domain.dto.ReservationResponseDTO;
import com.consultancy.reservations.domain.dto.UserDTO;
import com.consultancy.reservations.infrastructure.inputport.IReservationInputPort;
import com.consultancy.reservations.infrastructure.outputport.IProfessionalServicePort;
import com.consultancy.reservations.infrastructure.outputport.IReservationMethods;
import com.consultancy.reservations.infrastructure.outputport.IUserServicePort;
import org.springframework.stereotype.Service;


@Service
public class ReservationUseCase implements IReservationInputPort {

    private final IReservationMethods reservationMethods;

    private final IReservationMapper reservationMapper;

    private final IProfessionalServicePort professionalServicePort;

    private final IUserServicePort userServicePort;

    public ReservationUseCase(IReservationMethods reservationMethods, IReservationMapper reservationMapper, IProfessionalServicePort professionalServicePort, IUserServicePort userServicePort) {
        this.reservationMethods = reservationMethods;
        this.reservationMapper = reservationMapper;
        this.professionalServicePort = professionalServicePort;
        this.userServicePort = userServicePort;
    }

    @Override
    public ReservationResponseDTO createReservation(ReservationRequestDTO reservationRequestDTO, String token) throws UserNotFoundException {
        UserDTO user = userServicePort.getUser(reservationRequestDTO.getUserId(), token);
        ProfessionalDTO professional = professionalServicePort.getProfessional(reservationRequestDTO.getProfessionalId(), token);

        Reservation reservation = reservationMapper.reservationRequestDtoTOReservation(reservationRequestDTO);
        reservation.setState(ReservationState.PENDING);

        Reservation reservationNew = reservationMethods.save(reservation);

        ReservationResponseDTO response = reservationMapper.reservationToResponseDTO(reservationNew);
        response.setUser(user);
        response.setProfessional(professional);

        return response;
    }

    @Override
    public String updateReservationStatus(Long id, String status) throws ReservationNotFoundException, InvalidStateException {
        ReservationState newState;

        try {
            newState = ReservationState.valueOf(status.toUpperCase());
        } catch (IllegalArgumentException e) {
            throw new InvalidStateException("Invalid reservation state: " + status);
        }

        Reservation reservationDB = reservationMethods.findById(id).orElseThrow(() -> new ReservationNotFoundException("Reservation not found"));
        reservationDB.setState(newState);
        reservationMethods.save(reservationDB);

        return "Reservation updated successfully";
    }

    @Override
    public String deleteReservationById(Long id) throws ReservationNotFoundException {
        Reservation reservation = reservationMethods.findById(id).orElseThrow(() -> new ReservationNotFoundException("Reservation not found"));
        reservationMethods.deleteById(reservation.getId());

        return "Reservation deleted successfully";
    }
}