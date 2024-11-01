package com.consultancy.users.infrastructure.outputAdapter;

import com.consultancy.users.application.exception.RoleNotFoundException;
import com.consultancy.users.domain.Role;
import com.consultancy.users.infrastructure.outputPort.IRoleMethods;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class RoleRepositoryImpl implements IRoleMethods {

    private final IRoleRepository roleRepository;

    public RoleRepositoryImpl(IRoleRepository roleRepository) {
        this.roleRepository = roleRepository;
    }

    @Override
    public Role save(Role role) {
        return roleRepository.save(role);
    }

    @Override
    public Role findById(Long id) throws RoleNotFoundException {
        return roleRepository.findById(id).orElseThrow(() -> new RoleNotFoundException("Role not found"));
    }

    @Override
    public List<Role> findAll() {
        return roleRepository.findAll();
    }

    @Override
    public void deleteById(Long id) {
        roleRepository.deleteById(id);
    }
}
