package com.consultancy.users.application;

import com.consultancy.users.application.dto.UserRequestDTO;
import com.consultancy.users.application.dto.UserResponseDTO;
import com.consultancy.users.application.exception.RoleNotFoundException;
import com.consultancy.users.application.exception.UserNotFoundException;
import com.consultancy.users.application.mapper.IUserMapper;
import com.consultancy.users.domain.Role;
import com.consultancy.users.domain.UserEntity;
import com.consultancy.users.infrastructure.inputPort.IUserInputPort;
import com.consultancy.users.infrastructure.outputPort.IRoleMethods;
import com.consultancy.users.infrastructure.outputPort.IUserMethods;
import org.springframework.stereotype.Service;

@Service
public class UserUseCase implements IUserInputPort {

    private final IUserMethods userMethods;

    private final IRoleMethods roleMethods;

    private final IUserMapper userMapper;

    public UserUseCase(IUserMethods userMethods, IRoleMethods roleMethods, IUserMapper userMapper) {
        this.userMethods = userMethods;
        this.userMapper = userMapper;
        this.roleMethods = roleMethods;
    }

    @Override
    public UserResponseDTO createUser(UserRequestDTO userRequestDTO) throws RoleNotFoundException {
        Role role = roleMethods.findById(userRequestDTO.getRole().getId());

        UserEntity userEntity = userMapper.userRequestDtoToUserEntity(userRequestDTO);

        userEntity.setEnabled(true);
        userEntity.setAccountNotExpired(true);
        userEntity.setAccountNotLocked(true);
        userEntity.setCredentialNotExpired(true);
        userEntity.setRole(role);

        UserEntity userEntityNew = userMethods.save(userEntity);

        return userMapper.userEntityToUserResponseDto(userEntityNew);
    }

    @Override
    public UserResponseDTO updateUser(Long id, UserRequestDTO userRequestDTO) throws UserNotFoundException, RoleNotFoundException {
        UserEntity userEntity = userMethods.findById(id);
        Role role = roleMethods.findById(userRequestDTO.getRole().getId());

        userEntity.setName(userRequestDTO.getName());
        userEntity.setPassword(userRequestDTO.getPassword());
        userEntity.setEmail(userRequestDTO.getEmail());
        userEntity.setRole(role);

        UserEntity userEntityUpdated = userMethods.save(userEntity);

        return userMapper.userEntityToUserResponseDto(userEntityUpdated);

    }

    @Override
    public UserResponseDTO findUserById(Long id) throws UserNotFoundException {
        UserEntity userEntity = userMethods.findById(id);

        return userMapper.userEntityToUserResponseDto(userEntity);
    }

    @Override
    public UserResponseDTO findUserByEmail(String email) throws UserNotFoundException {
        UserEntity userEntity = userMethods.findByEmail(email);

        return userMapper.userEntityToUserResponseDto(userEntity);
    }

    @Override
    public String deleteUserById(Long id) throws UserNotFoundException {
        userMethods.findById(id);
        userMethods.deleteById(id);

        return "User deleted successfully";
    }
}
