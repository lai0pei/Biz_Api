package io.lab.core.modules.admin.dto;

import java.io.Serializable;
import java.util.UUID;


public record AdminDto(
        UUID id,
        String username,
        String password,
        String nickname,
        long roleId,
        Boolean enabled,
        Boolean superAdmin
) implements Serializable {
}

