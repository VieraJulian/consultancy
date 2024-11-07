package com.consultancy.users.infrastructure.outputPort;

import com.consultancy.users.application.exception.UserNotFoundException;
import com.consultancy.users.domain.UserEntity;

import javax.swing.text.html.Option;
import java.util.List;
import java.util.Optional;

public interface IUserMethods {

    public UserEntity save(UserEntity userEntity);
    public Optional<UserEntity> findById(Long id);
    public Optional<UserEntity> findByEmail(String email);
    public Optional<UserEntity> findByUsername(String username);
    public void deleteById(Long id);
}
