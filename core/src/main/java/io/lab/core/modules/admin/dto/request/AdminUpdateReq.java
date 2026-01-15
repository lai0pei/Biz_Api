package io.lab.core.modules.admin.dto.request;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.annotation.Nullable;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;

import java.util.UUID;


public record AdminUpdateReq(
        @Schema(requiredMode = Schema.RequiredMode.REQUIRED)
        @NotNull
        UUID id,

        @Schema(example = "admin")
        @NotNull
        String nickname,

        @Schema(example = "pass")
        @Nullable
        String password,

        @Schema(example = "1")
        Boolean enabled,

        @Schema(example = "0")
        Boolean superAdmin,

        @Schema(example = "1")
        @Min(1)
        long roleId
) { }