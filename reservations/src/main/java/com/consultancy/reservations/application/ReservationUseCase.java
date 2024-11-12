package com.consultancy.reservations.application;

import com.consultancy.reservations.application.mapper.IReservationMapper;
import com.consultancy.reservations.domain.dto.ReservationRequestDTO;
import com.consultancy.reservations.domain.dto.ReservationResponseDTO;
import com.consultancy.reservations.infrastructure.inputport.IReservationInputPort;
import com.consultancy.reservations.infrastructure.outputport.IReservationMethods;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ReservationUseCase implements IReservationInputPort {

    private final IReservationMethods reservationMethods;

    private final IReservationMapper reservationMapper;

    public ReservationUseCase(IReservationMethods reservationMethods, IReservationMapper reservationMapper) {
        this.reservationMethods = reservationMethods;
        this.reservationMapper = reservationMapper;
    }

    @Override
    public ReservationResponseDTO createReservation(ReservationRequestDTO reservationRequestDTO) {
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
