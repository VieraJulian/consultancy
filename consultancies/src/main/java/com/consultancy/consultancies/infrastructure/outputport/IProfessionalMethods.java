package com.consultancy.consultancies.infrastructure.outputport;

import com.consultancy.consultancies.application.exception.ProfessionalNotFoundException;
import com.consultancy.consultancies.domain.Professional;

import java.util.List;

public interface IProfessionalMethods {

    Professional saveProfessional(Professional professional);

    Professional getProfessionalById(Long id) throws ProfessionalNotFoundException;

    List<Professional> getAllProfessionals(int page, int size);

    void deleteProfessionalById(Long id);
}
