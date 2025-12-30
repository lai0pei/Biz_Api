package io.lab.core.api.admin.request;

import jakarta.annotation.Nonnull;
import jakarta.validation.constraints.Min;
import org.springframework.boot.context.properties.bind.DefaultValue;


public record AddDto(
        @Nonnull
        String username,
        @Nonnull
        String password,
        @Nonnull
        String name,
        @DefaultValue("1")
        Boolean isEnabled,
        @DefaultValue("0")
        Boolean isSuperAdmin,
        @Min(1)
        int roleId
) {
}