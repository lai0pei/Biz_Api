package io.lab.core.modules.admin.dto.projection;


import io.lab.core.modules.role.RoleMdl;

import java.util.Set;
import java.util.UUID;

public record AdminDetailDto(
        UUID id,
        String username,
        String nickname,
        RoleMdl role,
        Boolean enabled,
        Boolean superAdmin
) {
}
