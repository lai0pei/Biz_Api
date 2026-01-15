package io.lab.core.modules.login.dto.request;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotBlank;

public record CredentialReq(
        @Schema(example = "admin" ,requiredMode = Schema.RequiredMode.REQUIRED)
        @NotBlank(message = "username is required")
        String username,

        @Schema(example = "password" ,requiredMode = Schema.RequiredMode.REQUIRED)
        @NotBlank(message = "credential is required")
        String password
){
}
