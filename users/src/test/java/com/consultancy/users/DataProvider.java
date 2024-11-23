package com.consultancy.users;

import com.consultancy.users.application.dto.UserRequestDTO;
import com.consultancy.users.application.dto.UserResponseDTO;
import com.consultancy.users.domain.Permission;
import com.consultancy.users.domain.Role;
import com.consultancy.users.domain.UserEntity;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class DataProvider {

    public static List<UserEntity> userListMock() {
        return null;
    }

    public static Role getRole() {
        Set<Permission> permissionSet = new HashSet<>();
        Permission permission1 = Permission.builder()
                .id(1L).permissionName("CREATE")
                .build();

        Permission permission2 = Permission.builder()
                .id(2L)
                .permissionName("DELETE")
                .build();

        permissionSet.add(permission1);
        permissionSet.add(permission2);

        return Role.builder()
                .id(1L)
                .role("ADMIN")
                .permissions(permissionSet)
                .build();
    }

    public static UserRequestDTO getUserRequestDTO() {

        return UserRequestDTO.builder()
                .name("Alberto")
                .email("alberto2025@gmail.com")
                .password("1234")
                .role(getRole())
                .build();
    }

    public static UserEntity getUserEntity() {
        return UserEntity.builder()
                .id(1L)
                .name("Alberto")
                .email("alberto2025@gmail.com")
                .password("$2a$10$Z4XysU2WdVlgbpAgz0YedOQJNozLR2g7k7rLDDN9mR9gAscRJVocy")
                .enabled(true)
                .accountNotExpired(true)
                .accountNotLocked(true)
                .credentialNotExpired(true)
                .role(getRole())
                .build();
    }

    public static UserResponseDTO getUserResponseDTO() {
        return UserResponseDTO.builder()
                .id(1L)
                .name("Alberto")
                .email("alberto2025@gmail.com")
                .enabled(true)
                .accountNotExpired(true)
                .accountNotLocked(true)
                .credentialNotExpired(true)
                .role(getRole())
                .build();
    }

}
