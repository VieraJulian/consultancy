package com.consultancy.consultancies.infrastructure.inputAdapter;

import com.consultancy.consultancies.application.dto.AvailabilityDto;
import com.consultancy.consultancies.application.dto.ProfessionalDto;
import com.consultancy.consultancies.infrastructure.inputport.IAvailabilityInputPort;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/availability")
public class AvailabilityController {

    private final IAvailabilityInputPort availabilityInputPort;

    private static final Logger logger = LoggerFactory.getLogger(AvailabilityController.class);

    @Autowired
    public AvailabilityController(IAvailabilityInputPort availabilityInputPort) {
        this.availabilityInputPort = availabilityInputPort;
    }

    @PostMapping("/add/{professionalId}")
    public ResponseEntity<ProfessionalDto> addAvailability(@PathVariable Long professionalId, @RequestBody AvailabilityDto availabilityDto) {
        try {
            ProfessionalDto professionalDto = availabilityInputPort.addAvailability(professionalId, availabilityDto);
            return new ResponseEntity<>(professionalDto, HttpStatus.OK);
        } catch (Exception e) {
            logger.error("Error adding availability {}", String.valueOf(e));
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PutMapping("/update/{id}")
    public ResponseEntity<AvailabilityDto> updateAvailability(@PathVariable Long id, @RequestBody AvailabilityDto availabilityDto) {
        try {
            AvailabilityDto availabilityUpdated = availabilityInputPort.updateAvailability(id, availabilityDto);
            return new ResponseEntity<>(availabilityUpdated, HttpStatus.OK);
        } catch (Exception e) {
            logger.error("Error updating availability {}", String.valueOf(e));
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<String> deleteAvailabilityById(@PathVariable Long id) {
        try {
            String msj = availabilityInputPort.deleteAvailabilityById(id);
            return new ResponseEntity<>(msj, HttpStatus.OK);
        } catch (Exception e) {
            logger.error("Error deleting availability {}", String.valueOf(e));
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}
