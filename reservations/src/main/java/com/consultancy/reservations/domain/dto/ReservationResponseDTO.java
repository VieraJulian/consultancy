package com.consultancy.reservations.domain.dto;

import com.consultancy.reservations.domain.ReservationState;

import java.time.LocalDate;
import java.time.LocalTime;

public class ReservationResponseDTO {

    private Long id;
    private LocalDate reservationDate;
    private LocalTime reservationTime;
    private ReservationState state;
    private UserDTO user;
    private ProfessionalDTO professional;
}
