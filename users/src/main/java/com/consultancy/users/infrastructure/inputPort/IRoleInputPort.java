package com.consultancy.users.infrastructure.inputPort;

import com.consultancy.users.application.dto.RoleDTO;
import com.consultancy.users.application.dto.RoleRequestDTO;
import com.consultancy.users.application.exception.RoleNotFoundException;

import java.util.List;

public interface IRoleInputPort {

    public RoleDTO createRole(RoleRequestDTO roleRequestDTO);
    public RoleDTO updateRole(Long id, RoleRequestDTO roleRequestDTO) throws RoleNotFoundException;
    public List<RoleDTO> findAllRoles();
    public RoleDTO findRoleById(Long id) throws RoleNotFoundException;
    public String deleteRoleById(Long id) throws RoleNotFoundException;
}
