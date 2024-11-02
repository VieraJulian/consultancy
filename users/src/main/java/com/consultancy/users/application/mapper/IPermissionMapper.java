package com.consultancy.users.application.mapper;

import com.consultancy.users.application.dto.PermissionDTO;
import com.consultancy.users.application.dto.PermissionNameDTO;
import com.consultancy.users.domain.Permission;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface IPermissionMapper {

    PermissionDTO permissionToPermissionDto(Permission permission);
    Permission permissionNameDtoToPermission(PermissionNameDTO permissionNameDTO);
}
