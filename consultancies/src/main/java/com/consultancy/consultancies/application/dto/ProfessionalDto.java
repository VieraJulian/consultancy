package com.consultancy.consultancies.application.dto;

import lombok.*;

import java.math.BigDecimal;
import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class ProfessionalDto {

    private Long id;
    private String name;
    private String specialty;
    private BigDecimal price;
    private String description;
    private boolean enabled;
    private List<AvailabilityDto> availabilities;

}
