package io.lab.core.api.admin.request;

import jakarta.annotation.Nullable;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import lombok.Data;

@Data
public class SearchDto {
    @Min(0)
    private int page;
    @Min(5)
    @Max(500)
    private int size;
    @Nullable
    private String username;
    @Nullable
    private Boolean isEnabled;
    @Nullable
    private String lastLoginTime;
}

