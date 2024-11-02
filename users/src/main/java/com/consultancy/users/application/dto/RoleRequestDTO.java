package com.consultancy.users.application.dto;

import com.consultancy.users.domain.Permission;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Set;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class RoleRequestDTO {

    private String role;
    Set<Permission> permissions;
}
