package com.consultancy.users.infrastructure.outputAdapter;

import com.consultancy.users.application.exception.UserNotFoundException;
import com.consultancy.users.domain.UserEntity;
import com.consultancy.users.infrastructure.outputPort.IUserMethods;
import org.springframework.stereotype.Component;

@Component
public class UserRepositoryImpl implements IUserMethods {

    private final IUserRepository userRepository;

    public UserRepositoryImpl(IUserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public UserEntity save(UserEntity userEntity) {
        return userRepository.save(userEntity);
    }

    @Override
    public UserEntity findById(Long id) throws UserNotFoundException {
        return userRepository.findById(id).orElseThrow(() -> new UserNotFoundException("User not found"));
    }

    @Override
    public UserEntity findByEmail(String email) throws UserNotFoundException {
        return userRepository.findUserEntityByEmail(email).orElseThrow(() -> new UserNotFoundException("User not found"));
    }

    @Override
    public void deleteById(Long id) {
        userRepository.deleteById(id);
    }
}
