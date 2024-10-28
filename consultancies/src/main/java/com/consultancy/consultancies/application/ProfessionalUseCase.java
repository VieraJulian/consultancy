package com.consultancy.consultancies.application;

import com.consultancy.consultancies.application.dto.AvailabilityDto;
import com.consultancy.consultancies.application.dto.ProfessionalDto;
import com.consultancy.consultancies.application.exception.ProfessionalNotFoundException;
import com.consultancy.consultancies.application.mapper.IAvailabilityMapper;
import com.consultancy.consultancies.application.mapper.IProfessionalMapper;
import com.consultancy.consultancies.domain.Availability;
import com.consultancy.consultancies.domain.Professional;
import com.consultancy.consultancies.infrastructure.inputport.IProfessionalInputPort;
import com.consultancy.consultancies.infrastructure.outputport.IProfessionalMethods;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class ProfessionalUseCase implements IProfessionalInputPort {

    private final IProfessionalMethods professionalMethods;

    private final IProfessionalMapper professionalMapper;

    private final IAvailabilityMapper availabilityMapper;

    public ProfessionalUseCase(IProfessionalMethods professionalMethods, IProfessionalMapper professionalMapper, IAvailabilityMapper availabilityMapper) {
        this.professionalMethods = professionalMethods;
        this.professionalMapper = professionalMapper;
        this.availabilityMapper = availabilityMapper;
    }

    @Override
    public ProfessionalDto createProfessional(ProfessionalDto professionalDto) {
        Professional professional = professionalMapper.professionalDtoToProfessional(professionalDto);
        professional.setEnabled(true);

        Professional professionalNew = professionalMethods.saveProfessional(professional);

        return professionalMapper.professionalToProfessionalDto(professionalNew);
    }

    @Override
    public ProfessionalDto updateProfessional(Long id, ProfessionalDto professionalDto) throws ProfessionalNotFoundException {
        Professional professionalDB = professionalMethods.getProfessionalById(id);

        professionalDB.setName(professionalDto.getName());
        professionalDB.setSpecialty(professionalDto.getSpecialty());
        professionalDB.setPrice(professionalDto.getPrice());
        professionalDB.setDescription(professionalDto.getDescription());
        professionalDB.setEnabled(true);
        if (professionalDto.getAvailabilities() != null) {
            List<Availability> availabilityList = professionalDto.getAvailabilities().stream()
                    .map(availabilityMapper::availabilityDtoToAvailability)
                    .collect(Collectors.toList());
            professionalDB.setAvailabilities(availabilityList);
        }

        Professional professionalUpdated = professionalMethods.saveProfessional(professionalDB);

        return professionalMapper.professionalToProfessionalDto(professionalUpdated);
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
