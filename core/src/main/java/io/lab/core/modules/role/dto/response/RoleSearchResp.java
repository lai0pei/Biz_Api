package io.lab.core.modules.role.dto.response;

import java.time.LocalDateTime;

public record RoleSearchResp(
        Long id,
        String roleName,
        boolean enabled,
        LocalDateTime createdTime,
        LocalDateTime updatedTime
) {
}
