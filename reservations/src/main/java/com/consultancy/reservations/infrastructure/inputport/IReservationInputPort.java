package com.consultancy.reservations.infrastructure.inputport;

import com.consultancy.reservations.domain.dto.ReservationRequestDTO;
import com.consultancy.reservations.domain.dto.ReservationResponseDTO;

import java.util.List;

public interface IReservationInputPort {

    ReservationResponseDTO createReservation(ReservationRequestDTO reservationRequestDTO);
    List<ReservationResponseDTO> findReservationsByUserId(Long id);
    List<ReservationResponseDTO> findReservationsByProfessionalId(Long id);
    String deleteReservationById(Long id);
}
