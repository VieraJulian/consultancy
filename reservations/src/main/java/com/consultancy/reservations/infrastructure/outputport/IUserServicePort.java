package com.consultancy.reservations.infrastructure.outputport;

import com.consultancy.reservations.domain.dto.UserDTO;

public interface IUserServicePort {

    UserDTO getUser(Long userId);
}
