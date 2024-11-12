package com.consultancy.consultancies.infrastructure.outputAdapter;

import com.consultancy.consultancies.application.exception.ProfessionalNotFoundException;
import com.consultancy.consultancies.domain.Professional;
import com.consultancy.consultancies.infrastructure.outputport.IProfessionalMethods;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Optional;

@Component
public class ProfessionalRepositoryImpl implements IProfessionalMethods {

    private final IProfessionalRepository professionalRepository;

    @Autowired
    public ProfessionalRepositoryImpl(IProfessionalRepository professionalRepository) {
        this.professionalRepository = professionalRepository;
    }

    @Override
    public Professional saveProfessional(Professional professional) {
        return professionalRepository.save(professional);
    }

    @Override
    public Optional<Professional> getProfessionalById(Long id) {
        return professionalRepository.findById(id);
    }

    @Override
    public List<Professional> getAllProfessionals(int page, int size) {
        Pageable pageable = PageRequest.of(page - 1, size);
        return professionalRepository.findAllProfessionals(pageable);
    }

    @Override
    public void deleteProfessionalById(Long id) {
        professionalRepository.deleteById(id);
    }
}
