package com.consultancy.consultancies.application.dto;

import lombok.*;

import java.time.LocalTime;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class AvailabilityDto {

    private Long id;
    private String dayOfWeek;
    private String startTime;
    private String endTime;
}
