package io.lab.core.modules.role.dto.request;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotBlank;
import org.springframework.boot.context.properties.bind.DefaultValue;

public record RoleCreateReq(
        @Schema(example = "owner", requiredMode = Schema.RequiredMode.REQUIRED)
        @NotBlank(message = "rolename is required")
        String roleName,

        @Schema(example = "1", requiredMode = Schema.RequiredMode.REQUIRED)
        @DefaultValue("1")
        Boolean enabled
) {
}
