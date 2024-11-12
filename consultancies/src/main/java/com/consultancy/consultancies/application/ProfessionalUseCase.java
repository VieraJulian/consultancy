package com.consultancy.consultancies.application;

import com.consultancy.consultancies.application.dto.ProfessionalCreateDto;
import com.consultancy.consultancies.application.dto.ProfessionalDto;
import com.consultancy.consultancies.application.dto.ProfessionalUpdateDto;
import com.consultancy.consultancies.application.exception.ProfessionalNotFoundException;
import com.consultancy.consultancies.application.mapper.IProfessionalMapper;
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

    public ProfessionalUseCase(IProfessionalMethods professionalMethods, IProfessionalMapper professionalMapper) {
        this.professionalMethods = professionalMethods;
        this.professionalMapper = professionalMapper;
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
        Professional professionalDB = professionalMethods.getProfessionalById(id).orElseThrow(() -> new ProfessionalNotFoundException("Professional not found"));

        professionalDB.setName(professionalUpdateDto.getName());
        professionalDB.setSpecialty(professionalUpdateDto.getSpecialty());
        professionalDB.setPrice(professionalUpdateDto.getPrice());
        professionalDB.setDescription(professionalUpdateDto.getDescription());

        Professional professionalUpdated = professionalMethods.saveProfessional(professionalDB);

        return professionalMapper.professionalToProfessionalDto(professionalUpdated);
    }

    @Override
    public ProfessionalDto findProfessionalById(Long id) throws ProfessionalNotFoundException {
        Professional professionalDB = professionalMethods.getProfessionalById(id).orElseThrow(() -> new ProfessionalNotFoundException("Professional not found"));

        return professionalMapper.professionalToProfessionalDto(professionalDB);
    }

    @Override
    public List<ProfessionalDto> findAllProfessionals(int page, int size) {
        List<Professional> professionalList = professionalMethods.getAllProfessionals(page, size);

        return professionalList.stream()
                .map(professionalMapper::professionalToProfessionalDto)
                .collect(Collectors.toList());

    }

    @Override
    public String deleteProfessionalById(Long id) throws ProfessionalNotFoundException {
        professionalMethods.getProfessionalById(id);
        professionalMethods.deleteProfessionalById(id);

        return "Professional deleted successfully";
    }
}
