package com.consultancy.reservations.infrastructure.outputAdapter;

import com.consultancy.reservations.application.exception.UserNotFoundException;
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
    public UserDTO getUser(Long userId, String token) throws UserNotFoundException {
        UserDTO user = userServiceAdapter.getUser(userId, token);

        if (user == null) {
            throw new UserNotFoundException("User not found");
        }

        return user;
    }
}
