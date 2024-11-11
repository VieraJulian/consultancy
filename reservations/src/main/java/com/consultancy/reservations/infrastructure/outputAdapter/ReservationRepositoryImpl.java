package com.consultancy.reservations.infrastructure.outputAdapter;

import com.consultancy.reservations.domain.Reservation;
import com.consultancy.reservations.infrastructure.outputport.IReservationMethods;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

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
    public List<Reservation> findByUserId(Long userId) {
        return reservationRepository.findReservationsByUserId(userId);
    }

    @Override
    public List<Reservation> findByProfessionalId(Long professionalId) {
        return reservationRepository.findReservationsByProfessionalId(professionalId);
    }

    @Override
    public void deleteById(Long id) {
        reservationRepository.deleteById(id);
    }
}
