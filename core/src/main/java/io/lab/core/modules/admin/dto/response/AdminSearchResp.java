package io.lab.core.modules.admin.dto.response;

import java.time.LocalDateTime;
import java.util.UUID;

public record AdminSearchResp(
        UUID id,
        String username,
        String nickname,
        boolean enabled,
        boolean superAdmin,
        LocalDateTime lastLoginTime,
        LocalDateTime createdTime,
        LocalDateTime updatedTime
//        RoleMdl role
) {
}

