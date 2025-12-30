package io.lab.core.api.admin.securityDetail;


import io.lab.core.api.admin.AdminModel;
import lombok.Data;
import lombok.Getter;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.math.BigInteger;
import java.time.LocalDateTime;
import java.util.Collection;

@Data
@Getter
public class AdminDetail implements UserDetails {
    private String username;
    private String password;
    private long id;
    private long role_id;
    private String name;
    private boolean is_enabled;
    private Collection<? extends GrantedAuthority> authorities;
    private LocalDateTime last_login;
    private boolean isSuperAdmin;

    public AdminDetail(AdminModel adminModel, Collection<? extends GrantedAuthority> authorities) {
        this.id = adminModel.getId();
        this.username = adminModel.getUsername();
        this.password = adminModel.getPassword();
        this.role_id = adminModel.getRole().getId();
        this.name = adminModel.getName();
        this.is_enabled = adminModel.isEnabled();
        this.authorities = authorities;
        this.isSuperAdmin = adminModel.isSuperAdmin();
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return authorities;
    }

    @Override
    public String getPassword() {
        return "";
    }
}
