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
            return new ResponseEntity<>(reservationNew, HttpStatus.OK);
        } catch (Exception e) {
            logger.error("Error creating reservation {}", String.valueOf(e));
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}
