package com.consultancy.users.application;

import com.consultancy.users.application.dto.RoleDTO;
import com.consultancy.users.application.dto.RoleRequestDTO;
import com.consultancy.users.application.exception.PermissionNotFoundException;
import com.consultancy.users.application.exception.RoleNotFoundException;
import com.consultancy.users.application.mapper.IRoleMapper;
import com.consultancy.users.domain.Permission;
import com.consultancy.users.domain.Role;
import com.consultancy.users.infrastructure.inputPort.IRoleInputPort;
import com.consultancy.users.infrastructure.outputPort.IPermissionMethods;
import com.consultancy.users.infrastructure.outputPort.IRoleMethods;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.List;
import java.util.Objects;
import java.util.Set;
import java.util.stream.Collectors;

@Service
public class RoleUseCase implements IRoleInputPort {

    private final IRoleMethods roleMethods;

    private final IRoleMapper roleMapper;

    private final IPermissionMethods permissionMethods;

    @Autowired
    public RoleUseCase(IRoleMethods roleMethods, IRoleMapper roleMapper, IPermissionMethods permissionMethods) {
        this.roleMethods = roleMethods;
        this.roleMapper = roleMapper;
        this.permissionMethods = permissionMethods;
    }

    @Override
    public RoleDTO createRole(RoleRequestDTO roleRequestDTO) {
        Set<Permission> permissionsSet = roleRequestDTO.getPermissions().stream()
                .map(permission -> {
                    try {
                        return permissionMethods.findById(permission.getId());
                    } catch (PermissionNotFoundException e) {
                        return null;
                    }
                })
                .filter(Objects::nonNull)
                .collect(Collectors.toSet());

        Role role = roleMapper.roleRequestDtoToRole(roleRequestDTO);

        role.setPermissions(permissionsSet);

        Role roleNew = roleMethods.save(role);

        return roleMapper.roleToRoleDto(roleNew);
    }

    @Override
    public RoleDTO updateRole(Long id, RoleRequestDTO roleRequestDTO) throws RoleNotFoundException {
        Role roleDB = roleMethods.findById(id);

        Set<Permission> permissionsSet = roleRequestDTO.getPermissions().stream()
                .map(permission -> {
                    try {
                        return permissionMethods.findById(permission.getId());
                    } catch (PermissionNotFoundException e) {
                        return null;
                    }
                })
                .filter(Objects::nonNull)
                .collect(Collectors.toSet());

        roleDB.setRole(roleRequestDTO.getRole());
        roleDB.setPermissions(permissionsSet);

        Role roleUpdated = roleMethods.save(roleDB);

        return roleMapper.roleToRoleDto(roleUpdated);
    }

    @Override
    public List<RoleDTO> findAllRoles() {
        List<Role> roleList = roleMethods.findAll();

        return roleList.stream().map(roleMapper::roleToRoleDto)
                .collect(Collectors.toList());
    }

    @Override
    public RoleDTO findRoleById(Long id) throws RoleNotFoundException {
        Role roleDB = roleMethods.findById(id);

        return roleMapper.roleToRoleDto(roleDB);
    }

    @Override
    public String deleteRoleById(Long id) throws RoleNotFoundException {
        roleMethods.findById(id);
        roleMethods.deleteById(id);

        return "Role deleted successfully";
    }
}
