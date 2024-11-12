package com.consultancy.reservations.domain.dto;

import com.consultancy.reservations.domain.ReservationState;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;
import java.time.LocalTime;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class ReservationDTO {

    private Long id;
    private Long userId;
    private Long professionalId;
    private LocalDate reservationDate;
    private LocalTime reservationTime;
    private ReservationState state;

}
