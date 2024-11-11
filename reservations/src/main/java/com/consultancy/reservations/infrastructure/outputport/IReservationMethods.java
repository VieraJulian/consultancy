package com.consultancy.reservations.infrastructure.outputport;

import com.consultancy.reservations.domain.Reservation;

import java.util.List;

public interface IReservationMethods {

    Reservation save(Reservation reservation);
    List<Reservation> findByUserId(Long userId);
    List<Reservation> findByProfessionalId(Long professionalId);
    void deleteById(Long id);
}
