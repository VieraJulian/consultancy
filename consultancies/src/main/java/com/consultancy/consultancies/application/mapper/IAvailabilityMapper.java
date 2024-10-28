package com.consultancy.consultancies.application.mapper;

import com.consultancy.consultancies.application.dto.AvailabilityDto;
import com.consultancy.consultancies.domain.Availability;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface IAvailabilityMapper {
    @Mapping(target = "startTime", source = "startTime", dateFormat = "HH:mm:ss")
    @Mapping(target = "endTime", source = "endTime", dateFormat = "HH:mm:ss")
    AvailabilityDto availabilityToAvailabilityDto(Availability availability);

    @Mapping(target = "startTime", source = "startTime", dateFormat = "HH:mm:ss")
    @Mapping(target = "endTime", source = "endTime", dateFormat = "HH:mm:ss")
    Availability availabilityDtoToAvailability(AvailabilityDto availabilityDto);
}
