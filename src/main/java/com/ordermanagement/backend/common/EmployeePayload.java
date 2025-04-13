package com.ordermanagement.backend.common;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public record EmployeePayload(
        @NotNull(message = "Name is required")
        @NotBlank(message = "Name is required")
        String name,

        @NotNull(message = "Position is required")
        EmployeePosition position

        /*
         * TODO: Add the assignedMachine field
         * */
) {
}
