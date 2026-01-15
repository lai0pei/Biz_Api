package io.lab.core.modules.permission;

import lombok.Getter;

//there are third level permission
@Getter
public enum PermType {
    // Admin‑level permissions
    ADMIN_ADD,
    ADMIN_EDIT,
    ADMIN_DELETE,
    ADMIN_LIST,

    // Role‑level permissions
    ROLE_ADD,
    ROLE_EDIT,
    ROLE_DELETE,
    ROLE_LIST,

    // Menu‑level permissions
    MENU_LIST;

}
