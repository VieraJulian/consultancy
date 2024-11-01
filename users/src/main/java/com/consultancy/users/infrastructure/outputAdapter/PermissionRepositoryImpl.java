package com.consultancy.users.infrastructure.outputAdapter;

import com.consultancy.users.application.exception.PermissionNotFoundException;
import com.consultancy.users.domain.Permission;
import com.consultancy.users.infrastructure.outputPort.IPermissionMethods;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class PermissionRepositoryImpl implements IPermissionMethods {

    private final IPermissionRepository permissionRepository;

    public PermissionRepositoryImpl(IPermissionRepository permissionRepository) {
        this.permissionRepository = permissionRepository;
    }

    @Override
    public Permission save(Permission permission) {
        return permissionRepository.save(permission);
    }

    @Override
    public Permission findById(Long id) throws PermissionNotFoundException {
        return permissionRepository.findById(id).orElseThrow(() -> new PermissionNotFoundException("Permission not found"));
    }

    @Override
    public List<Permission> findAll() {
        return permissionRepository.findAll();
    }

    @Override
    public void deleteById(Long id) {
        permissionRepository.deleteById(id);
    }
}
