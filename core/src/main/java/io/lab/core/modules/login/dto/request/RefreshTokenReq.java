package io.lab.core.modules.login.dto.request;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotBlank;

public record RefreshTokenReq(
        @Schema(example = "bearer-token" ,requiredMode = Schema.RequiredMode.REQUIRED)
        @NotBlank(message = "refresh token is required")
        String refreshToken
) {
}
