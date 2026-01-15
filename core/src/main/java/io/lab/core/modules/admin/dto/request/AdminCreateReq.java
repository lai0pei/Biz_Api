package io.lab.core.modules.admin.dto.request;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import lombok.Builder;
import org.springframework.boot.context.properties.bind.DefaultValue;


@Builder
public record AdminCreateReq(
        @Schema(example = "admin", requiredMode = Schema.RequiredMode.REQUIRED)
        @NotBlank(message = "username is required")
        String username,

        @Schema(example = "admin", requiredMode = Schema.RequiredMode.REQUIRED)
        @NotBlank(message = "password is required")
        String password,

        @Schema(example = "admin", requiredMode = Schema.RequiredMode.REQUIRED)
        @NotBlank
        String nickname,

        @Schema(example = "1", requiredMode = Schema.RequiredMode.REQUIRED)
        @DefaultValue("1")
        Boolean enabled,

        @Schema(example = "0", requiredMode = Schema.RequiredMode.REQUIRED)
        @DefaultValue("0")
        Boolean superAdmin,

        @Schema(example = "1", requiredMode = Schema.RequiredMode.REQUIRED)
        @Min(1)
        Long roleId
) { }

