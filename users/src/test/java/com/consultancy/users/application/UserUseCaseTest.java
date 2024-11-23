package com.consultancy.users.application;

import com.consultancy.users.DataProvider;
import com.consultancy.users.application.dto.UserRequestDTO;
import com.consultancy.users.application.dto.UserResponseDTO;
import com.consultancy.users.application.exception.RoleNotFoundException;
import com.consultancy.users.application.mapper.IUserMapper;
import com.consultancy.users.domain.Permission;
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

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

import java.util.HashSet;
import java.util.Set;

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
    public void testUpdateUser() {

    }

    @Test
    public void testFindUserById() {

    }

    @Test
    public void testFindUserByEmail() {

    }

    @Test
    public void testDeleteUserById() {

    }

    @Test
    public void testEncryptPassword() {

    }
}
