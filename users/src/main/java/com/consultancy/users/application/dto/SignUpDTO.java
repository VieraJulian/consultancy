package com.consultancy.users.application.dto;

import jakarta.validation.constraints.NotBlank;

public record SignUpDTO(@NotBlank String name,
                        @NotBlank String password,
                        @NotBlank String email) {
}