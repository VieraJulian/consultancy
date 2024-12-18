package com.consultancy.consultancies.application.dto;

import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class AvailabilityDto {

    private Long id;
    private String dayOfWeek;
    private String startTime;
    private String endTime;
}
