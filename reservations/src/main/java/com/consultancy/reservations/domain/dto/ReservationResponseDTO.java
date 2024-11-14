package com.consultancy.reservations.domain.dto;

import com.consultancy.reservations.domain.ReservationState;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class ReservationResponseDTO {

    private Long id;
    private String reservationDate;
    private String reservationTime;
    private String state;
    private UserDTO user;
    private ProfessionalDTO professional;
}
