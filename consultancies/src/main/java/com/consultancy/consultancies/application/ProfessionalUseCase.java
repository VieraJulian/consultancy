package com.consultancy.consultancies.application;

import com.consultancy.consultancies.application.dto.ProfessionalDto;
import com.consultancy.consultancies.application.mapper.IProfessionalMapper;
import com.consultancy.consultancies.infrastructure.inputport.IProfessionalInputPort;
import com.consultancy.consultancies.infrastructure.outputport.IProfessionalMethods;

import java.util.List;

public class ProfessionalUseCase implements IProfessionalInputPort {

    private final IProfessionalMethods professionalMethods;

    private final IProfessionalMapper professionalMapper;

    public ProfessionalUseCase(IProfessionalMethods professionalMethods, IProfessionalMapper professionalMapper) {
        this.professionalMethods = professionalMethods;
        this.professionalMapper = professionalMapper;
    }

    @Override
    public ProfessionalDto createProfessional(ProfessionalDto professionalDto) {
        return null;
    }

    @Override
    public ProfessionalDto updateProfessional(Long id, ProfessionalDto professionalDto) {
        return null;
    }

    @Override
    public List<ProfessionalDto> findAllProfessionals(int page, int size) {
        return List.of();
    }

    @Override
    public String deleteProfessionalById(Long id) {
        return "";
    }
}
