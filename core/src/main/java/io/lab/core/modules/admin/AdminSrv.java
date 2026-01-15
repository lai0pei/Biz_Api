package io.lab.core.modules.admin;


import io.lab.core.modules.admin.dto.AdminDto;
import io.lab.core.modules.admin.dto.request.AdminCreateReq;
import io.lab.core.modules.admin.dto.request.AdminDeleteReq;
import io.lab.core.modules.admin.dto.request.AdminSearchReq;
import io.lab.core.modules.admin.dto.request.AdminUpdateReq;
import io.lab.core.modules.admin.dto.response.AdminSearchResp;
import io.lab.core.modules.exceptions.AppException;
import io.lab.core.modules.role.RoleRepo;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Slf4j
public class AdminSrv {

    private final AdminRepo adminRepo;
    private final PasswordEncoder passwordEncoder;
    private final AdminMpp adminMpp;
    private final RoleRepo roleRepo;

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
    public void createAdmin(AdminCreateReq add){
        String username = add.username();
        if (this.adminRepo.existsByUsername(username)) {
            throw new AppException("Username already exists!");
        }
        String password = passwordEncoder.encode(add.password());
        var admin = adminMpp.toModel(add);
        admin.setPassword(password);

        var role = this.roleRepo.findById(add.roleId()).orElseThrow(()-> new AppException("Role not exist"));
        admin.setRole(role);
        this.adminRepo.save(admin);
    }

    @Transactional
    public void updateAdmin(AdminUpdateReq update) {
        var admin = this.adminRepo.findById(update.id()).orElseThrow(() -> new AppException("Admin not found!"));
        admin.setNickname(update.nickname());
        admin.setSuperAdmin(update.superAdmin());
        admin.setEnabled(update.enabled());
        var role = this.roleRepo.findById(update.roleId()).orElseThrow(()-> new AppException("Role not exist"));
        admin.setRole(role);
    }

    @Transactional
    public void deleteAdmin(AdminDeleteReq adminDelete) {
        this.adminRepo.deleteById(adminDelete.id());
    }

    @Transactional(readOnly = true)
    public AdminMdl findAdminByUsername(String username) {
        return this.adminRepo.findByUsername(username).orElseThrow(() -> new AppException("User Not Found"));
    }

    @Transactional(readOnly = true)
    public Page<AdminSearchResp> listAllAdmin(AdminSearchReq adminSearchReq) {
        Pageable pageable = PageRequest.of(adminSearchReq.getPage(), adminSearchReq.getSize(), Sort.Direction.DESC, "id");
        return this.adminRepo.listAllAdmin(adminSearchReq, pageable);
    }
}




























