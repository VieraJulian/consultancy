package com.consultancy.consultancies.infrastructure.inputport;

import com.consultancy.consultancies.application.dto.ProfessionalDto;
import com.consultancy.consultancies.application.exception.ProfessionalNotFoundException;

import java.util.List;

public interface IProfessionalInputPort {

    ProfessionalDto createProfessional(ProfessionalDto professionalDto);

    ProfessionalDto updateProfessional(Long id, ProfessionalDto professionalDto) throws ProfessionalNotFoundException;

    List<ProfessionalDto> findAllProfessionals(int page, int size);

    String deleteProfessionalById(Long id);
}
