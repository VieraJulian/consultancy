package com.consultancy.users.application;

import com.consultancy.users.DataProvider;
import com.consultancy.users.application.dto.UserRequestDTO;
import com.consultancy.users.application.dto.UserResponseDTO;
import com.consultancy.users.application.exception.RoleNotFoundException;
import com.consultancy.users.application.exception.UserNotFoundException;
import com.consultancy.users.application.mapper.IUserMapper;
import com.consultancy.users.domain.Role;
import com.consultancy.users.domain.UserEntity;
import com.consultancy.users.infrastructure.outputPort.IRoleMethods;
import com.consultancy.users.infrastructure.outputPort.IUserMethods;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class UserUseCaseTest {

    @Mock
    private IUserMethods userMethods;
    @Mock
    private IRoleMethods roleMethods;
    @Mock
    private IUserMapper userMapper;

    @InjectMocks
    private UserUseCase userUseCase;

    private UserRequestDTO userRequestDTO;

    private UserResponseDTO userResponseDTO;

    private UserEntity userEntity;

    private Role role;

    @BeforeEach
    public void init() {
        userRequestDTO = DataProvider.getUserRequestDTO();
        userResponseDTO = DataProvider.getUserResponseDTO();
        userEntity = DataProvider.getUserEntity();
        role = DataProvider.getRole();

    }

    @Test
    public void testCreateUser() throws RoleNotFoundException {
        when(roleMethods.findById(anyLong())).thenReturn(role);
        when(userMethods.save(any(UserEntity.class))).thenReturn(userEntity);
        when(userMapper.userRequestDtoToUserEntity(any(UserRequestDTO.class))).thenReturn(userEntity);
        when(userMapper.userEntityToUserResponseDto(any(UserEntity.class))).thenReturn(userResponseDTO);

        UserResponseDTO userResponseDTO = userUseCase.createUser(userRequestDTO);

        verify(roleMethods).findById(anyLong());
        verify(userMethods).save(any(UserEntity.class));
        assertNotNull(userResponseDTO);
        assertEquals(userEntity.getId(), userResponseDTO.getId());
    }

    @Test
    public void testCreateUserThrowsRoleNotFoundException() throws RoleNotFoundException {
        when(roleMethods.findById(anyLong())).thenThrow(new RoleNotFoundException("Role not found"));

        assertThrows(RoleNotFoundException.class, () -> {
            userUseCase.createUser(userRequestDTO);
        });

        verify(userMethods, never()).save(any(UserEntity.class));
    }

    @Test
    public void testUpdateUser() throws RoleNotFoundException, UserNotFoundException {
        when(userMethods.findById(anyLong())).thenReturn(Optional.ofNullable(userEntity));
        when(roleMethods.findById(anyLong())).thenReturn(role);
        when(userMethods.save(any(UserEntity.class))).thenReturn(userEntity);
        when(userMapper.userEntityToUserResponseDto(any(UserEntity.class))).thenReturn(userResponseDTO);

        UserResponseDTO userResponseDTO = userUseCase.updateUser(1L, userRequestDTO);

        verify(roleMethods).findById(anyLong());
        verify(userMethods).save(any(UserEntity.class));
        assertNotNull(userResponseDTO);
        assertEquals(userEntity.getId(), userResponseDTO.getId());
    }

    @Test
    public void testUpdateUserThrowsRoleNotFoundException() throws RoleNotFoundException {
        when(userMethods.findById(anyLong())).thenReturn(Optional.ofNullable(userEntity));
        when(roleMethods.findById(anyLong())).thenThrow(new RoleNotFoundException("Role not found"));

        assertThrows(RoleNotFoundException.class, () -> {
            userUseCase.updateUser(1L, userRequestDTO);
        });

        verify(userMethods, never()).save(any(UserEntity.class));

    }

    @Test
    public void testUpdateUserThrowsUserNotFoundException() {
        when(userMethods.findById(anyLong())).thenReturn(Optional.empty());

        assertThrows(UserNotFoundException.class, () -> {
            userUseCase.updateUser(1L, userRequestDTO);
        });

        verify(userMethods, never()).save(any(UserEntity.class));

    }

    @Test
    public void testFindUserById() throws UserNotFoundException {
        when(userMethods.findById(anyLong())).thenReturn(Optional.ofNullable(userEntity));
        when(userMapper.userEntityToUserResponseDto(any(UserEntity.class))).thenReturn(userResponseDTO);

        UserResponseDTO userResponseDTO = userUseCase.findUserById(1L);

        verify(userMethods).findById(anyLong());
        verify(userMapper).userEntityToUserResponseDto(any(UserEntity.class));
        assertNotNull(userResponseDTO);
        assertEquals(userEntity.getId(), userResponseDTO.getId());
    }

    @Test
    public void testFindUserByIdThrowsNotFoundException() throws UserNotFoundException {
        when(userMethods.findById(anyLong())).thenReturn(Optional.empty());

        assertThrows(UserNotFoundException.class, () -> {
            userUseCase.findUserById(1L);
        });

        verify(userMapper, never()).userEntityToUserResponseDto(any(UserEntity.class));
    }

    @Test
    public void testFindUserByEmail() throws UserNotFoundException {
        when(userMethods.findByEmail(anyString())).thenReturn(Optional.ofNullable(userEntity));
        when(userMapper.userEntityToUserResponseDto(any(UserEntity.class))).thenReturn(userResponseDTO);

        UserResponseDTO userResponseDTO = userUseCase.findUserByEmail("alberto2025@gmail.com");

        verify(userMethods).findByEmail(anyString());
        verify(userMapper).userEntityToUserResponseDto(any(UserEntity.class));
        assertNotNull(userResponseDTO);
        assertEquals(userEntity.getId(), userResponseDTO.getId());
    }

    @Test
    public void testFindUserByEmailThrowsUserNotFound() {
        when(userMethods.findByEmail(anyString())).thenReturn(Optional.empty());

        assertThrows(UserNotFoundException.class, () -> {
            userUseCase.findUserByEmail("alberto2025@gmail.com");
        });

        verify(userMapper, never()).userEntityToUserResponseDto(any(UserEntity.class));
    }

    @Test
    public void testDeleteUserById() throws UserNotFoundException {
        when(userMethods.findById(anyLong())).thenReturn(Optional.ofNullable(userEntity));
        doNothing().when(userMethods).deleteById(anyLong());

        String msj = userUseCase.deleteUserById(1L);

        verify(userMethods).findById(anyLong());
        verify(userMethods).deleteById(anyLong());
        assertEquals("User deleted successfully", msj);

    }

    @Test
    public void testDeleteUserByIdThrowsUserNotFoundException() throws UserNotFoundException {
        when(userMethods.findById(anyLong())).thenReturn(Optional.empty());

        assertThrows(UserNotFoundException.class, () -> {
            userUseCase.findUserById(1L);
        });

        verify(userMethods, never()).deleteById(anyLong());
    }


    @Test
    public void testEncryptPassword() {
        String rawPassword = "1234";
        String encodedPassword = userUseCase.encryptPassword(rawPassword);

        BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();

        assertTrue(passwordEncoder.matches(rawPassword, encodedPassword));
    }


}
