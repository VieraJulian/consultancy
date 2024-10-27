package com.consultancy.consultancies.application.dto;

import jakarta.persistence.Column;

import java.time.LocalTime;

public class AvailabilityDto {

    private Long id;
    private String dayOfWeek;
    private LocalTime startTime;
    private LocalTime endTime;
}
