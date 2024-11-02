package com.consultancy.users.application.mapper;

import com.consultancy.users.application.dto.RoleDTO;
import com.consultancy.users.application.dto.RoleRequestDTO;
import com.consultancy.users.domain.Role;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface IRoleMapper {

    Role roleRequestDtoToRole(RoleRequestDTO roleRequestDTO);
    RoleDTO roleToRoleDto(Role role);
    RoleRequestDTO roleToRoleRequestDto(Role role);
}
