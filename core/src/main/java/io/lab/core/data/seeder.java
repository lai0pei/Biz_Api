package io.lab.core.data;

import io.lab.core.modules.admin.AdminMdl;
import io.lab.core.modules.admin.AdminRepo;
import io.lab.core.modules.menu.MenuMdl;
import io.lab.core.modules.menu.MenuRank;
import io.lab.core.modules.menu.MenuRepo;
import io.lab.core.modules.menu.MenuSig;
import io.lab.core.modules.permission.*;
import io.lab.core.modules.role.RoleMdl;
import io.lab.core.modules.role.RoleRepo;
import io.lab.core.modules.role.RoleSrv;
import io.lab.core.modules.role.dto.request.PermGrant;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

import java.util.Arrays;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@Component
@Slf4j
public class seeder implements CommandLineRunner {
    @Autowired
    private AdminRepo adminRepo;

    @Autowired
    RoleRepo roleRepo;

    @Autowired
    PermRepo permRepo;

    @Autowired
    MenuRepo menuRepo;

    @Autowired
    RoleSrv roleSrv;

    @Autowired
    PasswordEncoder passwordEncoder;


    @Override
    public void run(String... args) throws Exception {

        this.addMenu();
        this.addPerm();
        this.addAdmin();
        this.addPermGrant();
    }

    private void addAdmin() {
        RoleMdl role = this.addRole();

        var admin = AdminMdl.builder()
                .username("admin")
                .password(passwordEncoder.encode("admin"))
                .nickname("adminH")
                .enabled(true)
                .superAdmin(false)
                .role(role)
                .build();

        var superadmin = AdminMdl.builder()
                .username("admin2")
                .password(passwordEncoder.encode("admin2"))
                .nickname("adminHo")
                .enabled(true)
                .superAdmin(true)
                .role(role)
                .build();

        adminRepo.saveAll(List.of(superadmin, admin));
    }

    private RoleMdl addRole() {
        var role = RoleMdl.builder()
                .roleName("MasterRole")
                .enabled(true)
                .build();

        return roleRepo.save(role);

    }

    private void addMenu() {


        var grantParent = MenuMdl.builder()
                .id(1L)
                .menuSig(MenuSig.SysMng)
                .parentId(0L)
                .menuRank(MenuRank.Lv1)
                .sort(0)
                .build();

        var parent = MenuMdl.builder()
                .id(100L)
                .menuSig(MenuSig.AdmGrpMng)
                .parentId(1L)
                .menuRank(MenuRank.Lv2)
                .sort(0)
                .build();

        var adm = MenuMdl.builder()
                .id(1000L)
                .menuSig(MenuSig.AdminMng)
                .parentId(100L)
                .menuRank(MenuRank.Lv3)
                .sort(0)
                .build();

        var role = MenuMdl.builder()
                .id(1001L)
                .menuSig(MenuSig.RoleMng)
                .parentId(100L)
                .menuRank(MenuRank.Lv3)
                .sort(1)
                .build();

        var menu = MenuMdl.builder()
                .id(1002L)
                .menuSig(MenuSig.MenuMng)
                .menuRank(MenuRank.Lv3)
                .parentId(100L)
                .sort(2)
                .build();

        this.menuRepo.saveAll(List.of(grantParent, parent, adm, role, menu));
    }

    private void addPerm() {
        var menu = menuRepo.findByMenuSig(MenuSig.AdminMng);
        // Assuming pmList is a List<PermMdl> and menu is already defined
        List<PermMdl> pmList =Arrays.stream(PermType.values()).map(permType -> PermMdl.builder().permission_type(permType).menu(menu).build()).toList();


        permRepo.saveAll(pmList);
    }


    private void addPermGrant() {
         List<PermMdl> permMils = this.permRepo.findAll();
         Set<Long> ids = permMils.stream().map(PermMdl::getId).collect(Collectors.toSet());
         this.roleSrv.approvePermGrant(PermGrant.builder().roleId(1L).ids(ids).build());
    }


}
