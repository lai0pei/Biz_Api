package io.lab.core.modules.menu.dto.request;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;

public record MenuCreateReq(

        @Schema(example = "1", requiredMode = Schema.RequiredMode.REQUIRED)
        @Min(1)
        @Max(50)
        Integer sort
) {
}
