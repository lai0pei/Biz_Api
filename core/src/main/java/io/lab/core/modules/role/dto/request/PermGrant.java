package io.lab.core.modules.role.dto.request;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotEmpty;
import lombok.Builder;

import java.util.Set;


@Builder
public record PermGrant(
        @Schema(example = "1", requiredMode = Schema.RequiredMode.REQUIRED)
        Long roleId,

        //empty mean to clear all the ids
        @Schema(example = "[1,2,3,4]", requiredMode = Schema.RequiredMode.REQUIRED)
        Set<Long> ids
) {
}
