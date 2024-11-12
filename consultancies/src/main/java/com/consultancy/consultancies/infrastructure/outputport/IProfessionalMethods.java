package com.consultancy.consultancies.infrastructure.outputport;

import com.consultancy.consultancies.domain.Professional;

import java.util.List;
import java.util.Optional;

public interface IProfessionalMethods {

    Professional saveProfessional(Professional professional);

    Optional<Professional> getProfessionalById(Long id);

    List<Professional> getAllProfessionals(int page, int size);

    void deleteProfessionalById(Long id);
}
