package com.ordermanagement.backend.common;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public record LoginPayload(
        @NotNull(message = "Email is required")
        @NotBlank(message = "Email is required")
        @Email(message = "Invalid email format")
        String email,


        @NotNull(message = "Password is required")
        @NotBlank(message = "Password is required")
        String password
) {
}
