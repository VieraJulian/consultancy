package com.consultancy.users.infrastructure.outputPort;

import com.consultancy.users.application.exception.UserNotFoundException;
import com.consultancy.users.domain.UserEntity;

import java.util.List;

public interface IUserMethods {

    public UserEntity save(UserEntity userEntity);
    public UserEntity findById(Long id) throws UserNotFoundException;
    public UserEntity findByEmail(String email) throws UserNotFoundException;
    public UserEntity findByUsername(String username);
    public void deleteById(Long id);
}
