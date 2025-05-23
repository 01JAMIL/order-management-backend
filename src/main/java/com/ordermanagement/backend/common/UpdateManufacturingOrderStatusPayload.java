package com.ordermanagement.backend.common;

import jakarta.validation.constraints.NotNull;

public record UpdateManufacturingOrderStatusPayload(
        @NotNull(message = "Status is required")
        ManufacturingOrderStatus status
) {
}
