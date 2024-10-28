package com.consultancy.consultancies.application.mapper;

import com.consultancy.consultancies.application.dto.ProfessionalDto;
import com.consultancy.consultancies.domain.Professional;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring", uses = IAvailabilityMapper.class)
public interface IProfessionalMapper {

    ProfessionalDto professionalToProfessionalDto(Professional professional);

    Professional professionalDtoToProfessional(ProfessionalDto professionalDto);
}
