package com.consultancy.users.application.dto;

import com.consultancy.users.domain.Permission;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;
import java.util.Set;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class RoleDTO {

    private Long id;
    private String role;
    Set<Permission> permissions;
}
