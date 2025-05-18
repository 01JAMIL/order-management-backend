package com.ordermanagement.backend.common;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public record RegisterPayload(
        @NotNull(message = "First name is required")
        @NotBlank(message = "First name is required")
        String firstName,

        @NotNull(message = "Last name is required")
        @NotBlank(message = "Last name is required")
        String lastName,

        @NotNull(message = "Email is required")
        @NotBlank(message = "Email is required")
        @Email(message = "Invalid email format")
        String email,


        @NotNull(message = "Password is required")
        @NotBlank(message = "Password is required")
        String password
) {
}
