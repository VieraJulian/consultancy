package com.consultancy.consultancies.infrastructure.inputportadapter;

import com.consultancy.consultancies.application.ProfessionalUseCase;
import com.consultancy.consultancies.application.dto.ProfessionalDto;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/professionals")
public class ProfessionalController {

    private final ProfessionalUseCase professionalUseCase;

    private static final Logger logger = LoggerFactory.getLogger(ProfessionalController.class);

    @Autowired
    public ProfessionalController(ProfessionalUseCase professionalUseCase) {
        this.professionalUseCase = professionalUseCase;
    }

    @PostMapping("/create")
    public ResponseEntity<ProfessionalDto> createProfessional(@RequestBody ProfessionalDto professionalDto){
        try {
            ProfessionalDto professionalCreated = professionalUseCase.createProfessional(professionalDto);
            return new ResponseEntity<>(professionalCreated, HttpStatus.CREATED);
        } catch (Exception e) {
            logger.error("Error creating professional {}", String.valueOf(e));
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PutMapping("/update/{id}")
    public ResponseEntity<ProfessionalDto> updateProfessional(@PathVariable Long id, @RequestBody ProfessionalDto professionalDto){
        try {
            ProfessionalDto professionalUpdated = professionalUseCase.updateProfessional(id, professionalDto);
            return new ResponseEntity<>(professionalUpdated, HttpStatus.CREATED);
        } catch (Exception e) {
            logger.error("Error editing professional {}", String.valueOf(e));
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}
