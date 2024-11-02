package com.consultancy.users.infrastructure.inputPort;

import com.consultancy.users.application.dto.UserRequestDTO;
import com.consultancy.users.application.dto.UserResponseDTO;
import com.consultancy.users.application.exception.RoleNotFoundException;
import com.consultancy.users.application.exception.UserNotFoundException;

public interface IUserInputPort {

    public UserResponseDTO createUser(UserRequestDTO userRequestDTO) throws RoleNotFoundException;
    public UserResponseDTO updateUser(Long id, UserRequestDTO userRequestDTO) throws UserNotFoundException, RoleNotFoundException;
    public UserResponseDTO findUserById(Long id) throws UserNotFoundException;
    public UserResponseDTO findUserByEmail(String email) throws UserNotFoundException;
    public String deleteUserById(Long id) throws UserNotFoundException;

}
