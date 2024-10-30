package com.consultancy.consultancies.infrastructure.outputport;

import com.consultancy.consultancies.application.exception.AvailabilityNotFoundException;
import com.consultancy.consultancies.application.exception.ProfessionalNotFoundException;
import com.consultancy.consultancies.domain.Availability;

public interface IAvailabilityMethods {

    Availability getAvailabilityById(Long id) throws ProfessionalNotFoundException, AvailabilityNotFoundException;

    Availability saveAvailability(Availability availability);

    void deleteAvailabilityById(Long id);
}
