package com.consultancy.reservations.infrastructure.outputAdapter;

import com.consultancy.reservations.domain.Reservation;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface IReservationRepository extends JpaRepository<Reservation, Long> {

    @Query(value = "SELECT r FROM Reservation r WHERE userId = :userId")
    List<Reservation> findReservationsByUserId(@Param("userId") Long userId);

    @Query(value = "SELECT r FROM Reservation r WHERE professionalId = :professionalId")
    List<Reservation> findReservationsByProfessionalId(@Param("professionalId") Long professionalId);
}
