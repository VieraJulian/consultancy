package com.consultancy.reservations.infrastructure.outputport;

import com.consultancy.reservations.domain.Reservation;

import java.util.List;
import java.util.Optional;

public interface IReservationMethods {

    Reservation save(Reservation reservation);
    Optional<Reservation> findById(Long id);
    void deleteById(Long id);
}
