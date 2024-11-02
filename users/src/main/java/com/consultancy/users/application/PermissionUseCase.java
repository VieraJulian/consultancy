package com.consultancy.users.application;

import com.consultancy.users.application.dto.PermissionDTO;
import com.consultancy.users.application.dto.PermissionNameDTO;
import com.consultancy.users.application.exception.PermissionNotFoundException;
import com.consultancy.users.application.mapper.IPermissionMapper;
import com.consultancy.users.domain.Permission;
import com.consultancy.users.infrastructure.inputPort.IPermissionInputPort;
import com.consultancy.users.infrastructure.outputPort.IPermissionMethods;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class PermissionUseCase implements IPermissionInputPort {

    private final IPermissionMethods permissionMethods;
    private final IPermissionMapper permissionMapper;

    public PermissionUseCase(IPermissionMethods permissionMethods, IPermissionMapper permissionMapper) {
        this.permissionMethods = permissionMethods;
        this.permissionMapper = permissionMapper;
    }

    @Override
    public PermissionDTO createPermission(PermissionNameDTO permissionNameDTO) {
        Permission permission = permissionMapper.permissionNameDtoToPermission(permissionNameDTO);

        Permission permissionCreated = permissionMethods.save(permission);

        return permissionMapper.permissionToPermissionDto(permissionCreated);
    }

    @Override
    public PermissionDTO findPermissionById(Long id) throws PermissionNotFoundException {
        Permission permissionDB = permissionMethods.findById(id);

        return permissionMapper.permissionToPermissionDto(permissionDB);
    }

    @Override
    public List<PermissionDTO> findAllPermissions() {
        List<Permission> permissionList = permissionMethods.findAll();

        return permissionList.stream()
                .map(permissionMapper::permissionToPermissionDto)
                .collect(Collectors.toList());
    }

    @Override
    public String deletePermissionById(Long id) throws PermissionNotFoundException {
        permissionMethods.findById(id);
        permissionMethods.deleteById(id);

        return "Permission deleting successfully";
    }
}
