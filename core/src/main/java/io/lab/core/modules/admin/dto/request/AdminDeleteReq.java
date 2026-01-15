package io.lab.core.modules.admin.dto.request;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.annotation.Nonnull;

import java.util.UUID;


public record AdminDeleteReq(
        @Schema(requiredMode = Schema.RequiredMode.REQUIRED)
        @Nonnull
        UUID id
) { }
