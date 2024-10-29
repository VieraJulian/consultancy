package com.consultancy.consultancies.application.mapper;

import com.consultancy.consultancies.application.dto.AvailabilityCreateDto;
import com.consultancy.consultancies.application.dto.AvailabilityDto;
import com.consultancy.consultancies.domain.Availability;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Mappings;

@Mapper(componentModel = "spring", imports = {java.time.LocalTime.class})
public interface IAvailabilityMapper {

    @Mappings({
            @Mapping(target = "startTime", source = "startTime", dateFormat = "HH:mm:ss"),
            @Mapping(target = "endTime", source = "endTime", dateFormat = "HH:mm:ss")
    })
    Availability availabilityDtoToAvailability(AvailabilityDto availabilityDto);

    @Mappings({
            @Mapping(target = "startTime", source = "startTime", dateFormat = "HH:mm:ss"),
            @Mapping(target = "endTime", source = "endTime", dateFormat = "HH:mm:ss")
    })
    AvailabilityDto availabilityToAvailabilityDto(Availability availability);

    @Mappings({
            @Mapping(target = "startTime", source = "startTime", dateFormat = "HH:mm:ss"),
            @Mapping(target = "endTime", source = "endTime", dateFormat = "HH:mm:ss")
    })
    Availability availabilityCreateDtoToAvailability(AvailabilityCreateDto availabilityCreateDto);

    @Mappings({
            @Mapping(target = "startTime", source = "startTime", dateFormat = "HH:mm:ss"),
            @Mapping(target = "endTime", source = "endTime", dateFormat = "HH:mm:ss")
    })
    AvailabilityCreateDto availabilityToAvailabilityCreateDto(Availability availability);
}
