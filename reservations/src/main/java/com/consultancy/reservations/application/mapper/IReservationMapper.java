package com.consultancy.reservations.application.mapper;

import com.consultancy.reservations.domain.Reservation;
import com.consultancy.reservations.domain.dto.ReservationRequestDTO;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface IReservationMapper {

    @Mapping(source = "reservationDate", target = "reservationDate", dateFormat = "yyyy-MM-dd")
    @Mapping(source = "reservationTime", target = "reservationTime", dateFormat = "HH:mm:ss")
    Reservation reservationRequestDtoTOReservation(ReservationRequestDTO reservationRequestDTO);

}
