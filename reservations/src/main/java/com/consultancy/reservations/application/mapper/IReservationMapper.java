package com.consultancy.reservations.application.mapper;

import com.consultancy.reservations.domain.Reservation;
import com.consultancy.reservations.domain.ReservationState;
import com.consultancy.reservations.domain.dto.ReservationRequestDTO;
import com.consultancy.reservations.domain.dto.ReservationResponseDTO;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Named;

@Mapper(componentModel = "spring")
public interface IReservationMapper {

    @Mapping(source = "reservationDate", target = "reservationDate", dateFormat = "yyyy-MM-dd")
    @Mapping(source = "reservationTime", target = "reservationTime", dateFormat = "HH:mm:ss")
    Reservation reservationRequestDtoTOReservation(ReservationRequestDTO reservationRequestDTO);

    @Mapping(source = "reservationDate", target = "reservationDate", dateFormat = "yyyy-MM-dd")
    @Mapping(source = "reservationTime", target = "reservationTime", dateFormat = "HH:mm:ss")
    @Mapping(source = "state", target = "state", qualifiedByName = "stateToString")
    ReservationResponseDTO reservationToResponseDTO(Reservation reservation);

    @Named("stateToString") default String stateToString(ReservationState state) {
        return state != null ? state.name() : null;
    }

}
