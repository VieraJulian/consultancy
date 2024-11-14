package com.consultancy.reservations.infrastructure.outputAdapter;

import com.consultancy.reservations.domain.Reservation;
import com.consultancy.reservations.infrastructure.outputport.IReservationMethods;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Optional;

@Component
public class ReservationRepositoryImpl implements IReservationMethods {

    private final IReservationRepository reservationRepository;

    @Autowired
    public ReservationRepositoryImpl(IReservationRepository reservationRepository) {
        this.reservationRepository = reservationRepository;
    }

    @Override
    public Reservation save(Reservation reservation) {
        return reservationRepository.save(reservation);
    }

    @Override
    public Optional<Reservation> findById(Long id) {
        return reservationRepository.findById(id);
    }

    @Override
    public void deleteById(Long id) {
        reservationRepository.deleteById(id);
    }
}
