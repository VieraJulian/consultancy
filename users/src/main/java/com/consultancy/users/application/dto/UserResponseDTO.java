package com.consultancy.users.application.dto;

import com.consultancy.users.domain.Role;
import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class UserResponseDTO {

    private Long id;
    private String name;
    private String email;
    private boolean enabled;
    private boolean accountNotExpired;
    private boolean accountNotLocked;
    private boolean credentialNotExpired;
    private Role role;
}
