package io.lab.core.common;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public abstract class PageReq {
    @Schema(example = "0", requiredMode = Schema.RequiredMode.REQUIRED)
    @NotNull(message = "Page must set")
    @Min(value = 0, message = "Page must be non-negative")
    private Integer page;

    @Schema(example = "10", requiredMode = Schema.RequiredMode.REQUIRED)
    @NotNull(message = "Size must set")
    @Min(value = 5, message = "Size must be at least 5")
    @Max(value = 500, message = "Size must not exceed 500")
    private Integer size;
}
