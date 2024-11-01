package com.consultancy.consultancies.infrastructure.inputport;

import com.consultancy.consultancies.application.dto.AvailabilityDto;
import com.consultancy.consultancies.application.dto.ProfessionalDto;
import com.consultancy.consultancies.application.exception.AvailabilityNotFoundException;
import com.consultancy.consultancies.application.exception.ProfessionalNotFoundException;
import com.consultancy.consultancies.domain.Availability;

public interface IAvailabilityInputPort {

    ProfessionalDto addAvailability(Long professionalId, AvailabilityDto availability) throws ProfessionalNotFoundException;

    AvailabilityDto updateAvailability(Long id, AvailabilityDto availability) throws AvailabilityNotFoundException, ProfessionalNotFoundException;

    String deleteAvailabilityById(Long id) throws AvailabilityNotFoundException, ProfessionalNotFoundException;
}
