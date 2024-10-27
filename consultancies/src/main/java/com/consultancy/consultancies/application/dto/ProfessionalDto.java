package com.consultancy.consultancies.application.dto;

import java.math.BigDecimal;
import java.util.List;

public class ProfessionalDto {

    private Long id;
    private String name;
    private String specialty;
    private BigDecimal price;
    private String description;
    private boolean enabled;
    private List<AvailabilityDto> availabilitiesDto;

}
