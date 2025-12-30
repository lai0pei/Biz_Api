package io.lab.core.api.admin.request;

import jakarta.annotation.Nonnull;
import jakarta.validation.constraints.Min;


public record UpdateDto(
        @Nonnull
        Long id,
        @Nonnull
        String name,
        boolean isEnabled,
        boolean isSuperAdmin,
        @Min(1)
        int roleId
) {
}