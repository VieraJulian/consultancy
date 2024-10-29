package com.consultancy.consultancies.application;

import com.consultancy.consultancies.application.dto.ProfessionalCreateDto;
import com.consultancy.consultancies.application.dto.ProfessionalDto;
import com.consultancy.consultancies.application.dto.ProfessionalUpdateDto;
import com.consultancy.consultancies.application.exception.ProfessionalNotFoundException;
import com.consultancy.consultancies.application.mapper.IAvailabilityMapper;
import com.consultancy.consultancies.application.mapper.IProfessionalMapper;
import com.consultancy.consultancies.domain.Professional;
import com.consultancy.consultancies.infrastructure.inputport.IProfessionalInputPort;
import com.consultancy.consultancies.infrastructure.outputport.IAvailabilityMethods;
import com.consultancy.consultancies.infrastructure.outputport.IProfessionalMethods;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class ProfessionalUseCase implements IProfessionalInputPort {

    private final IProfessionalMethods professionalMethods;

    private final IAvailabilityMethods availabilityMethods;

    private final IProfessionalMapper professionalMapper;

    private final IAvailabilityMapper availabilityMapper;

    public ProfessionalUseCase(IProfessionalMethods professionalMethods, IAvailabilityMethods availabilityMethods, IProfessionalMapper professionalMapper, IAvailabilityMapper availabilityMapper) {
        this.professionalMethods = professionalMethods;
        this.availabilityMethods = availabilityMethods;
        this.professionalMapper = professionalMapper;
        this.availabilityMapper = availabilityMapper;
    }

    @Override
    public ProfessionalDto createProfessional(ProfessionalCreateDto professionalCreateDto) {
        Professional professionalInfo = professionalMapper.professionalCreateDtoToProfessional(professionalCreateDto);
        professionalInfo.setEnabled(true);

        Professional professionalNew = professionalMethods.saveProfessional(professionalInfo);

        return professionalMapper.professionalToProfessionalDto(professionalNew);
    }

    @Override
    public ProfessionalDto updateProfessional(Long id, ProfessionalUpdateDto professionalUpdateDto) throws ProfessionalNotFoundException {
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
