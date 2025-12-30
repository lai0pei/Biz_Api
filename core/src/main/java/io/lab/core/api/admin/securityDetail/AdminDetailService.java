package io.lab.core.api.admin.securityDetail;

import io.lab.core.api.admin.AdminModel;
import io.lab.core.api.admin.AdminRepository;
import io.lab.core.api.model.AuthorityModel;
import io.lab.core.api.repo.AuthorityRepository;
import io.lab.core.api.role.RoleModel;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;

import java.util.Collection;
import java.util.stream.Collectors;

@Component
public class AdminDetailService implements UserDetailsService {

    private final AdminRepository adminRepository;
    private final AuthorityRepository authorityRepository;

    public AdminDetailService(AdminRepository adminRepository, AuthorityRepository authorityRepository) {
        this.adminRepository = adminRepository;
        this.authorityRepository = authorityRepository;
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        AdminModel adminModel = adminRepository.findByUsername(username).orElseThrow(() -> new UsernameNotFoundException(username));
        RoleModel roleModel = adminModel.getRole();
        Collection<? extends GrantedAuthority> authorities = this.authorityRepository.findPermissionsByRole(roleModel)
                .stream()
                .map(AuthorityModel::toString)
                .map(SimpleGrantedAuthority::new)
                .collect(Collectors.toList());
        return new AdminDetail(adminModel, authorities);
    }
}
