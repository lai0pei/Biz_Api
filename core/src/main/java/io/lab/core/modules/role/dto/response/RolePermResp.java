package io.lab.core.modules.role.dto.response;

import io.lab.core.modules.permission.PermType;

import java.util.List;

public record RolePermResp(
        Long id,
        List<Perm> permList
) {
    public record Perm(
            Long Id,
            PermType permType,
            Boolean checked
    ){}
}
