package com.consultancy.consultancies.application.mapper;

import com.consultancy.consultancies.application.dto.ProfessionalDto;
import com.consultancy.consultancies.domain.Professional;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface IProfessionalMapper {

    ProfessionalDto professionalToProfessionalDto(Professional professional);
}
