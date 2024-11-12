package com.consultancy.reservations.infrastructure.outputAdapter;

import com.consultancy.reservations.domain.dto.UserDTO;
import com.consultancy.reservations.infrastructure.outputport.IUserServicePort;
import org.springframework.stereotype.Component;

@Component
public class UserServiceAdapterImpl implements IUserServicePort {

    private final IUserServiceAdapter userServiceAdapter;

    public UserServiceAdapterImpl(IUserServiceAdapter userServiceAdapter) {
        this.userServiceAdapter = userServiceAdapter;
    }

    @Override
    public UserDTO getUser(Long userId) {
        return userServiceAdapter.getUser(userId);
    }
}
