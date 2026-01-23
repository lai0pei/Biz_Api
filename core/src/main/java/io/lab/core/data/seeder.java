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
    private  PreloadData preloadData;

    @Override
    public void run(String... args) throws Exception {
        preloadData.shoot();
    }



}
