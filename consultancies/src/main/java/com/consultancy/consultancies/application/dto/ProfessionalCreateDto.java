package com.consultancy.consultancies.application.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.math.BigDecimal;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class ProfessionalCreateDto {

    private String name;
    private String specialty;
    private BigDecimal price;
    private String description;
    private List<AvailabilityCreateDto> availabilities;

}
