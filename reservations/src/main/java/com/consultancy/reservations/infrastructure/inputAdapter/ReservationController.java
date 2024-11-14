package com.consultancy.reservations.infrastructure.inputAdapter;

import com.consultancy.reservations.application.ReservationUseCase;
import com.consultancy.reservations.domain.dto.ReservationRequestDTO;
import com.consultancy.reservations.domain.dto.ReservationResponseDTO;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/reservations")
public class ReservationController {

    private final ReservationUseCase reservationUseCase;

    private static final Logger logger = LoggerFactory.getLogger(ReservationController.class);

    @Autowired
    public ReservationController(ReservationUseCase reservationUseCase) {
        this.reservationUseCase = reservationUseCase;
    }

    @PostMapping(value = "/create", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<ReservationResponseDTO> createReservation(@RequestHeader("Authorization") String token, @RequestBody ReservationRequestDTO reservationRequestDTO) {
        try {
            ReservationResponseDTO reservationNew = reservationUseCase.createReservation(reservationRequestDTO, token);
            return new ResponseEntity<>(reservationNew, HttpStatus.CREATED);
        } catch (Exception e) {
            logger.error("Error creating reservation {}", String.valueOf(e));
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PutMapping("/{id}/status")
    public ResponseEntity<String> updateReservationStatus(@PathVariable Long id, @RequestParam String status) {
        try {
            String msj = reservationUseCase.updateReservationStatus(id, status);
            return new ResponseEntity<>(msj, HttpStatus.OK);
        } catch (Exception e) {
            logger.error("Error updating reservation {}", String.valueOf(e));
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<String> deleteReservation(@PathVariable Long id) {
        try {
            String msj = reservationUseCase.deleteReservationById(id);
            return new ResponseEntity<>(msj, HttpStatus.OK);
        } catch (Exception e) {
            logger.error("Error deleting reservation {}", String.valueOf(e));
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}
