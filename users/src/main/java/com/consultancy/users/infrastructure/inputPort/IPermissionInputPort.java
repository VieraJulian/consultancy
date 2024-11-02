package com.consultancy.users.infrastructure.inputPort;

import com.consultancy.users.application.dto.PermissionDTO;
import com.consultancy.users.application.dto.PermissionNameDTO;
import com.consultancy.users.application.exception.PermissionNotFoundException;
import com.consultancy.users.domain.Permission;

import java.util.List;

public interface IPermissionInputPort {

    public PermissionDTO createPermission(PermissionNameDTO permissionNameDTO);
    public PermissionDTO findPermissionById(Long id) throws PermissionNotFoundException;
    public List<PermissionDTO> findAllPermissions();
    public String deletePermissionById(Long id) throws PermissionNotFoundException;
}
