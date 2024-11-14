package com.consultancy.reservations.infrastructure.inputport;

import com.consultancy.reservations.application.exception.ReservationNotFoundException;
import com.consultancy.reservations.application.exception.UserNotFoundException;
import com.consultancy.reservations.domain.dto.ReservationRequestDTO;
import com.consultancy.reservations.domain.dto.ReservationResponseDTO;

import java.util.List;

public interface IReservationInputPort {

    ReservationResponseDTO createReservation(ReservationRequestDTO reservationRequestDTO, String token) throws UserNotFoundException;
    String deleteReservationById(Long id) throws ReservationNotFoundException;
}
