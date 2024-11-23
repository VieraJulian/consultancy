package com.consultancy.users.application.dto;

import com.consultancy.users.domain.Role;
import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class UserRequestDTO {

    private String name;
    private String email;
    private String password;
    private Role role;
}
