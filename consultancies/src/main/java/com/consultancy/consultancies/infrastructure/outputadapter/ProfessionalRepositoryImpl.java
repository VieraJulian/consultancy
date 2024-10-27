package com.consultancy.consultancies.infrastructure.outputadapter;

import com.consultancy.consultancies.application.exception.ProfessionalNotFoundException;
import com.consultancy.consultancies.domain.Professional;
import com.consultancy.consultancies.infrastructure.outputport.IProfessionalMethods;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Component;

import java.util.List;

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
    public Professional getProfessionalById(Long id) throws ProfessionalNotFoundException {
        return professionalRepository.findById(id).orElseThrow(() -> new ProfessionalNotFoundException("Professional not found"));
    }

    @Override
    public List<Professional> getAllProfessionals(int page, int size) {
        Pageable pageable = PageRequest.of(page, size);
        return professionalRepository.getAllProfessionals(pageable);
    }

    @Override
    public void deleteProfessionalById(Long id) {
        professionalRepository.deleteById(id);
    }
}
