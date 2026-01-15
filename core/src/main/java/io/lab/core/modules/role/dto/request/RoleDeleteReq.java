package io.lab.core.modules.role.dto.request;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.annotation.Nonnull;

public record RoleDeleteReq(
        @Schema(example = "1", requiredMode = Schema.RequiredMode.REQUIRED)
        @Nonnull
        Long id
) {
}
