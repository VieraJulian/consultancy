package com.consultancy.reservations.domain.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.math.BigDecimal;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class ProfessionalDTO {

    private Long id;
    private String name;
    private String specialty;
    private BigDecimal price;
    private String description;
    private boolean enabled;
}
