package com.consultancy.consultancies.infrastructure.outputadapter;

import com.consultancy.consultancies.infrastructure.outputport.IAvailabilityMethods;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class AvailabilityRepositoryImpl implements IAvailabilityMethods {

    private final IAvailabilityRepository availabilityRepository;

    @Autowired
    public AvailabilityRepositoryImpl(IAvailabilityRepository availabilityRepository) {
        this.availabilityRepository = availabilityRepository;
    }

    @Override
    public void deleteAvailabilityById(Long id) {
        availabilityRepository.deleteById(id);
    }
}
