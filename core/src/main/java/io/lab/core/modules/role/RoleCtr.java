package io.lab.core.modules.role;


import io.lab.core.common.SucRes;
import io.lab.core.modules.exceptions.AppException;
import io.lab.core.modules.role.dto.request.*;
import io.lab.core.modules.role.dto.response.RoleSearchResp;
import io.lab.core.utils.PermAccess;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RestController
@RequestMapping("/modules/role")
@Tag(name = "Role")
@SecurityRequirement(name = "Bearer-token")
public class RoleCtr {

    private final RoleSrv roleSrv;

    RoleCtr(RoleSrv roleSrv) {
        this.roleSrv = roleSrv;
    }

    @Operation(summary = "Add an admin")
    @PreAuthorize("@Permission.check('ROLE_ADD')")
    @PostMapping("/add")
    public ResponseEntity<?> add(@Valid @RequestBody RoleCreateReq roleCreateReq) {
        try {
            this.roleSrv.addRole(roleCreateReq);
        } catch (Exception e) {
            throw new AppException("Add failed");
        }
        return ResponseEntity
                .status(HttpStatus.OK)
                .body(SucRes.empty());
    }

    @Operation(summary = "Update an admin")
    @PreAuthorize("@Permission.check('ROLE_EDIT')")
    @PostMapping("/update")
    public ResponseEntity<?> update(@Valid @RequestBody RoleUpdateReq roleUpdateReq) {
        try {
            this.roleSrv.updateRole(roleUpdateReq);
        } catch (Exception ex) {
            throw new AppException("Update failed");
        }
        return ResponseEntity
                .status(HttpStatus.OK)
                .body(SucRes.empty());
    }

    @Operation(summary = "Delete an admin")
    @PreAuthorize("@Permission.check('ROLE_DELETE')")
    @PostMapping("/delete")
    public ResponseEntity<?> delete(@Valid @RequestBody RoleDeleteReq roleDeleteReq) {
        try {
            this.roleSrv.deleteRole(roleDeleteReq);
        } catch (Exception e) {
            throw new AppException("Delete failed");
        }
        return ResponseEntity
                .status(HttpStatus.OK)
                .body(SucRes.empty());
    }

    @Operation(summary = "Fetch list of admin plus filter search")
    @PreAuthorize("@Permission.check('ROLE_LIST')")
    @PostMapping("/list")
    public ResponseEntity<?> list(@Valid @RequestBody RoleSearchReq roleSearchReq) {
        Page<RoleSearchResp> s = this.roleSrv.listRole(roleSearchReq);
        return ResponseEntity
                .status(HttpStatus.OK)
                .body(SucRes.ok(s));
    }

    @Operation(summary = "Fetch list of admin plus filter search")
    @PreAuthorize("@Permission.check('ROLE_EDIT')")
    @PostMapping("/permGrant")
    public ResponseEntity<?> permGrantIds(@Valid @RequestBody PermGrant permGrant) {
        try {
            this.roleSrv.approvePermGrant(permGrant);
        } catch (Exception e) {
            throw new AppException("Approved failed");
        }
        return ResponseEntity
                .status(HttpStatus.OK)
                .body(SucRes.empty());
    }


    @Operation(summary = "Fetch list of admin plus filter search")
    @PreAuthorize("@Permission.check('ROLE_EDIT')")
    @PostMapping("/getPermGrant")
    public ResponseEntity<?> getPermGrant(@Valid @RequestBody PermGrant permGrant) {
        try {
            var res = this.roleSrv.getPermGrant(permGrant.roleId());
            return ResponseEntity
                    .status(HttpStatus.OK)
                    .body(SucRes.ok(res));
        } catch (Exception e) {
            throw new AppException("Approved failed");
        }

    }

}
