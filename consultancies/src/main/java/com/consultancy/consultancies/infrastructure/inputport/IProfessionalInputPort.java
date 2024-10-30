package com.consultancy.consultancies.infrastructure.inputport;

import com.consultancy.consultancies.application.dto.ProfessionalCreateDto;
import com.consultancy.consultancies.application.dto.ProfessionalDto;
import com.consultancy.consultancies.application.dto.ProfessionalUpdateDto;
import com.consultancy.consultancies.application.exception.ProfessionalNotFoundException;

import java.util.List;

public interface IProfessionalInputPort {

    ProfessionalDto createProfessional(ProfessionalCreateDto professionalCreateDto);

    ProfessionalDto updateProfessional(Long id, ProfessionalUpdateDto professionalUpdateDto) throws ProfessionalNotFoundException;

    List<ProfessionalDto> findAllProfessionals(int page, int size);

    String deleteProfessionalById(Long id) throws ProfessionalNotFoundException;
}
