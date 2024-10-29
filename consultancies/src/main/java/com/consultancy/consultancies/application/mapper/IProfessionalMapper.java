package com.consultancy.consultancies.application.mapper;

import com.consultancy.consultancies.application.dto.ProfessionalCreateDto;
import com.consultancy.consultancies.application.dto.ProfessionalDto;
import com.consultancy.consultancies.domain.Professional;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring", uses = IAvailabilityMapper.class)
public interface IProfessionalMapper {

    ProfessionalDto professionalToProfessionalDto(Professional professional);

    Professional professionalDtoToProfessional(ProfessionalDto professionalDto);

    @Mapping(target = "availabilities", source = "availabilities")
    Professional professionalCreateDtoToProfessional(ProfessionalCreateDto createDto);

    ProfessionalCreateDto professionalToProfessionalCreateDto(Professional professional);
}
