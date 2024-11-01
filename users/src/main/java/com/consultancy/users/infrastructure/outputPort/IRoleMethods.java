package com.consultancy.users.infrastructure.outputPort;

import com.consultancy.users.application.exception.RoleNotFoundException;
import com.consultancy.users.domain.Role;

import java.util.List;

public interface IRoleMethods {

    public Role save(Role role);
    public Role findById(Long id) throws RoleNotFoundException;
    public List<Role> findAll();
    public void deleteById(Long id);

}
