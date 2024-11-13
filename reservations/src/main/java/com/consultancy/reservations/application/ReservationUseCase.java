package com.consultancy.reservations.application;

import com.consultancy.reservations.application.exception.UserNotFoundException;
import com.consultancy.reservations.application.mapper.IReservationMapper;
import com.consultancy.reservations.domain.dto.ProfessionalDTO;
import com.consultancy.reservations.domain.dto.ReservationRequestDTO;
import com.consultancy.reservations.domain.dto.ReservationResponseDTO;
import com.consultancy.reservations.domain.dto.UserDTO;
import com.consultancy.reservations.infrastructure.inputport.IReservationInputPort;
import com.consultancy.reservations.infrastructure.outputport.IProfessionalServicePort;
import com.consultancy.reservations.infrastructure.outputport.IReservationMethods;
import com.consultancy.reservations.infrastructure.outputport.IUserServicePort;
import org.springframework.stereotype.Service;

import java.util.List;

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
        
        return null;
    }

    @Override
    public List<ReservationResponseDTO> findReservationsByUserId(Long id) {
        return List.of();
    }

    @Override
    public List<ReservationResponseDTO> findReservationsByProfessionalId(Long id) {
        return List.of();
    }

    @Override
    public String deleteReservationById(Long id) {
        return "";
    }
}