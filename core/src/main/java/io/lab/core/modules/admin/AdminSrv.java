package io.lab.core.modules.admin;


import io.lab.core.modules.admin.dto.AdminDto;
import io.lab.core.modules.admin.dto.projection.AdminDetailDto;
import io.lab.core.modules.admin.dto.request.AdminCreateReq;
import io.lab.core.modules.admin.dto.request.AdminDeleteReq;
import io.lab.core.modules.admin.dto.request.AdminSearchReq;
import io.lab.core.modules.admin.dto.request.AdminUpdateReq;
import io.lab.core.modules.admin.dto.response.AdminSearchResp;
import io.lab.core.modules.exceptions.AppException;
import io.lab.core.modules.role.RoleMdl;
import io.lab.core.modules.role.RoleRepo;
import lombok.extern.slf4j.Slf4j;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.CachePut;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Collection;
import java.util.UUID;

@Service
@Slf4j
public class AdminSrv {

    private final AdminRepo adminRepo;
    private final PasswordEncoder passwordEncoder;
    private final AdminMpp adminMpp;
    private final RoleRepo roleRepo;
    private final static String ADMIN_CACHE_NAME = "admin";

    public AdminSrv(
            AdminRepo adminRepo,
            PasswordEncoder passwordEncoder,
            AdminMpp adminMpp,
            RoleRepo roleRepo) {
        this.adminRepo = adminRepo;
        this.passwordEncoder = passwordEncoder;
        this.adminMpp = adminMpp;
        this.roleRepo = roleRepo;
    }

    @Transactional
    @CachePut(value = ADMIN_CACHE_NAME, key="#result.id")
    public AdminMdl createAdmin(AdminCreateReq add){
        String username = add.username();
        if (this.adminRepo.existsByUsername(username)) {
            throw new AppException("Username already exists!");
        }
        String password = passwordEncoder.encode(add.password());
        var admin = adminMpp.toModel(add);
        admin.setPassword(password);

        var role = this.roleRepo.findById(add.roleId()).orElseThrow(()-> new AppException("Role not exist"));
        admin.setRole(role);
        return this.adminRepo.save(admin);
    }

    @Transactional
    @CachePut(value = ADMIN_CACHE_NAME, key="#result.id")
    public AdminDetailDto updateAdmin(AdminUpdateReq update) {
        var admin = this.adminRepo.findById(update.id()).orElseThrow(() -> new AppException("Admin not found!"));
        admin.setNickname(update.nickname());
        admin.setSuperAdmin(update.superAdmin());
        admin.setEnabled(update.enabled());
        var role = this.roleRepo.findById(update.roleId()).orElseThrow(()-> new AppException("Role not exist"));
        admin.setRole(role);
        return adminMpp.toAdminDetail(admin);
    }

    @Transactional
    @CacheEvict(value = ADMIN_CACHE_NAME, key="#adminDelete.id")
    public void deleteAdmin(AdminDeleteReq adminDelete) {
        this.adminRepo.deleteById(adminDelete.id());
    }

    @Transactional(readOnly = true)
    public AdminMdl findAdminByUsername(String username) {
       return this.adminRepo.findByUsername(username).orElseThrow(() -> new AppException("User Not Found"));
    }

    //for jwtTokenFilter
    @Transactional(readOnly = true)
    @Cacheable(value = ADMIN_CACHE_NAME, key="#uuid")
    public AdminDetailDto findAdminById(UUID uuid) {
        var admin = this.adminRepo.findById(uuid).orElseThrow(() -> new AppException("User Not Found"));
        return adminMpp.toAdminDetail(admin);
    }

    @Transactional(readOnly = true)
    public Page<AdminSearchResp> listAllAdmin(AdminSearchReq adminSearchReq) {
        Pageable pageable = PageRequest.of(adminSearchReq.getPage(), adminSearchReq.getSize(), Sort.Direction.DESC, "id");
        return this.adminRepo.listAllAdmin(adminSearchReq, pageable);
    }
}




























