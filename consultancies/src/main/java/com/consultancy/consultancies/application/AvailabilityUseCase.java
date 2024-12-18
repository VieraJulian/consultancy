package com.consultancy.consultancies.application;

import com.consultancy.consultancies.application.dto.AvailabilityDto;
import com.consultancy.consultancies.application.dto.ProfessionalDto;
import com.consultancy.consultancies.application.exception.AvailabilityNotFoundException;
import com.consultancy.consultancies.application.exception.ProfessionalNotFoundException;
import com.consultancy.consultancies.application.mapper.IAvailabilityMapper;
import com.consultancy.consultancies.application.mapper.IProfessionalMapper;
import com.consultancy.consultancies.domain.Availability;
import com.consultancy.consultancies.domain.Professional;
import com.consultancy.consultancies.infrastructure.inputport.IAvailabilityInputPort;
import com.consultancy.consultancies.infrastructure.outputport.IAvailabilityMethods;
import com.consultancy.consultancies.infrastructure.outputport.IProfessionalMethods;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalTime;

@Service
public class AvailabilityUseCase implements IAvailabilityInputPort {

    private final IAvailabilityMethods availabilityMethods;

    private final IProfessionalMethods professionalMethods;

    private final IAvailabilityMapper availabilityMapper;

    private final IProfessionalMapper professionalMapper;

    @Autowired
    public AvailabilityUseCase(IAvailabilityMethods availabilityMethods, IAvailabilityMapper availabilityMapper, IProfessionalMethods professionalMethods, IProfessionalMapper professionalMapper){
        this.availabilityMethods = availabilityMethods;
        this.availabilityMapper = availabilityMapper;
        this.professionalMethods = professionalMethods;
        this.professionalMapper = professionalMapper;
    }

    @Override
    public ProfessionalDto addAvailability(Long professionalId, AvailabilityDto availability) throws ProfessionalNotFoundException {
        Professional professionalDB = professionalMethods.getProfessionalById(professionalId).orElseThrow(() -> new ProfessionalNotFoundException("Professional not found"));
        Availability availabilityConverted = availabilityMapper.availabilityDtoToAvailability(availability);

        professionalDB.getAvailabilities().add(availabilityConverted);

        return professionalMapper.professionalToProfessionalDto(professionalMethods.saveProfessional(professionalDB));
    }

    @Override
    public AvailabilityDto updateAvailability(Long id, AvailabilityDto availability) throws AvailabilityNotFoundException, ProfessionalNotFoundException {
        Availability availabilityDB = availabilityMethods.getAvailabilityById(id);

        availabilityDB.setDayOfWeek(availability.getDayOfWeek());
        availabilityDB.setStartTime(LocalTime.parse(availability.getStartTime()));
        availabilityDB.setEndTime(LocalTime.parse(availability.getEndTime()));

        return availabilityMapper.availabilityToAvailabilityDto(availabilityMethods.saveAvailability(availabilityDB));
    }

    @Override
    public String deleteAvailabilityById(Long id) throws AvailabilityNotFoundException, ProfessionalNotFoundException {
        availabilityMethods.getAvailabilityById(id);
        availabilityMethods.deleteAvailabilityById(id);

        return "Availability deleted successfully";
    }
}
