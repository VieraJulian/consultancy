package com.consultancy.users.application.dto;

import jakarta.validation.constraints.NotBlank;

public record AuthLoginDTO(@NotBlank String email,
                           @NotBlank String password) {
}
