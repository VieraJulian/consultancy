package com.consultancy.users.infrastructure.outputPort;

import com.consultancy.users.application.exception.PermissionNotFoundException;
import com.consultancy.users.domain.Permission;

import java.util.List;

public interface IPermissionMethods {

    public Permission save(Permission permission);
    public Permission findById(Long id) throws PermissionNotFoundException;
    public List<Permission> findAll();
    public void deleteById(Long id);
}
