package io.lab.core.api.admin.request;

import jakarta.annotation.Nonnull;
import lombok.Data;

@Data
public class DeleteDto {
    @Nonnull
    private Long id;
}
