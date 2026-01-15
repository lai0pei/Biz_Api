package io.lab.core.modules.admin.securityDetail;

import io.lab.core.modules.admin.AdminMdl;
import io.lab.core.modules.admin.AdminSrv;
import io.lab.core.modules.admin.dto.AdminDto;
import io.lab.core.modules.role.RoleMdl;
import io.lab.core.modules.role.RoleSrv;
import lombok.extern.slf4j.Slf4j;
import org.jspecify.annotations.NullMarked;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.util.Collection;
import java.util.Collections;
import java.util.stream.Collectors;

@Component
@Slf4j
public class AdminDetailService implements UserDetailsService {

    private final AdminSrv adminSrv;
    private final RoleSrv roleSrv;

    public AdminDetailService(
            AdminSrv adminSrv,
            RoleSrv roleSrv) {
        this.adminSrv = adminSrv;
        this.roleSrv = roleSrv;
    }

    @Override
    @NullMarked
    @Transactional(readOnly = true)
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        //implement cache fetch here
        AdminMdl admin = this.adminSrv.findAdminByUsername(username);
        RoleMdl role = admin.getRole();
        Collection<? extends GrantedAuthority> authorities = role.getPermMdl()
                .stream()
                .map(permMdl -> new SimpleGrantedAuthority(permMdl.getPermission_type().toString()))
                .toList();

        return new User(
                admin.getUsername(),
                admin.getPassword(),
                admin.getEnabled(),
                true,
                true,
                true,
                authorities
        );
    }
}
