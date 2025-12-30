package io.lab.core.api.admin.response;

import java.time.LocalDateTime;

public record SearchResult(
        long id,
        String username,
        String name,
        boolean isEnabled,
        boolean isSuperAdmin,
        LocalDateTime lastLoginTime,
        LocalDateTime createdTime,
        LocalDateTime updatedTime
) {
}
