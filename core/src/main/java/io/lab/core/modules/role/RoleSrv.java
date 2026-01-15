package io.lab.core.modules.role;


import io.lab.core.modules.exceptions.AppException;
import io.lab.core.modules.permission.PermMdl;
import io.lab.core.modules.permission.PermRepo;
import io.lab.core.modules.role.dto.request.*;
import io.lab.core.modules.role.dto.response.RolePermResp;
import io.lab.core.modules.role.dto.response.RoleSearchResp;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.HashSet;
import java.util.Set;

@Slf4j
@Service
public class RoleSrv {
    private final RoleRepo roleRepo;
    private final RoleMpp roleMpp;
    private final PermRepo permRepo;

    public RoleSrv(RoleRepo roleRepo, RoleMpp roleMpp, PermRepo permRepo) {
        this.roleRepo = roleRepo;
        this.roleMpp = roleMpp;
        this.permRepo = permRepo;
    }

    public void addRole(RoleCreateReq roleCreateReq) {
        String roleName = roleCreateReq.roleName();
        if (this.roleRepo.findByRoleName(roleName).isPresent()) {
            throw new AppException("already exists");
        }
        this.roleRepo.save(this.roleMpp.toRoleMdl(roleCreateReq));
    }

    @Transactional
    public void updateRole(RoleUpdateReq roleUpdateReq) {
        RoleMdl role = this.roleRepo.findById(roleUpdateReq.id()).orElseThrow(() -> new AppException("not exists"));
        role.setRoleName(roleUpdateReq.roleName());
        role.setEnabled(roleUpdateReq.enabled());
    }

    public void deleteRole(RoleDeleteReq roleDeleteReq) {
        this.roleRepo.deleteById(roleDeleteReq.id());
    }

    public Page<RoleSearchResp> listRole(RoleSearchReq roleSearchReq) {
        Pageable pageable = PageRequest.of(roleSearchReq.getPage(), roleSearchReq.getSize(), Sort.Direction.DESC, "id");
        return this.roleRepo.searchRoleList(roleSearchReq, pageable);
    }

    @Transactional
    public void approvePermGrant(PermGrant permGrant) {
        RoleMdl role = this.roleRepo.findById(permGrant.roleId())
                .orElseThrow(() -> new AppException("Role not found"));
        var perms = permRepo.findAllById(permGrant.ids());
        role.setPermMdl(new HashSet<>(perms));
        this.roleRepo.save(role);
    }

    @Transactional(readOnly = true)
    public RolePermResp getPermGrant(Long roleId){
        RoleMdl role = this.roleRepo.findById(roleId)
                .orElseThrow(() -> new AppException("Role not found"));

        var allPermissions = permRepo.findAll();

        var rolePermIds = role.getPermMdl().stream()
                .map(PermMdl::getId)
                .collect(java.util.stream.Collectors.toSet());

        var permList = allPermissions.stream()
                .map(perm -> new RolePermResp.Perm(
                        perm.getId(),
                        perm.getPermission_type(),
                        rolePermIds.contains(perm.getId())
                ))
                .toList();

        return new RolePermResp(roleId, permList);
    }

}
