package io.lab.core.modules.admin;

import io.lab.core.common.SucRes;
import io.lab.core.modules.admin.dto.request.AdminCreateReq;
import io.lab.core.modules.admin.dto.request.AdminDeleteReq;
import io.lab.core.modules.admin.dto.request.AdminSearchReq;
import io.lab.core.modules.admin.dto.request.AdminUpdateReq;
import io.lab.core.modules.admin.dto.response.AdminSearchResp;
import io.lab.core.modules.exceptions.AppException;
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

import java.security.Permission;

@Slf4j
@RestController
@RequestMapping("/modules/admin")
@Tag(name = "Admin Management")
@SecurityRequirement(name = "Bearer-token")
public class AdminCtr {

    private final AdminSrv adminSrv;

    AdminCtr(AdminSrv adminSrv) {
        this.adminSrv = adminSrv;
    }

    @Operation(summary = "Add an admin")
    @PreAuthorize("@Permission.check('ADMIN_ADD')")
    @PostMapping("/add")
    public ResponseEntity<?> add(@Valid @RequestBody AdminCreateReq adminCreateReq) {
        try {
            this.adminSrv.createAdmin(adminCreateReq);
        } catch (Exception e) {
            throw new AppException("Add failed");
        }

        return ResponseEntity
                .status(HttpStatus.OK)
                .body(SucRes.empty());
    }

    @Operation(summary = "Update an admin")
    @PreAuthorize("@Permission.check('ADMIN_EDIT')")
    @PostMapping("/update")
    public ResponseEntity<?> update(@Valid @RequestBody AdminUpdateReq adminUpdateReq) {
        try {
            this.adminSrv.updateAdmin(adminUpdateReq);
        } catch (Exception ex) {
            throw new AppException("Update failed");
        }
        return ResponseEntity
                .status(HttpStatus.OK)
                .body(SucRes.empty());
    }

    @Operation(summary = "Delete an admin")
    @PreAuthorize("@Permission.check('ADMIN_DELETE')")
    @PostMapping("/delete")
    public ResponseEntity<?> delete(@RequestBody AdminDeleteReq adminDelete) {
        try {
            this.adminSrv.deleteAdmin(adminDelete);
        } catch (Exception e) {
            throw new AppException("Delete failed");
        }
        return ResponseEntity
                .status(HttpStatus.OK)
                .body(SucRes.empty());
    }

    @Operation(summary = "Fetch list of admin plus filter search")
    @PreAuthorize("@Permission.check('ADMIN_LIST')")
    @PostMapping("/list")
    public ResponseEntity<?> list(@Valid @RequestBody AdminSearchReq adminSearchReq) {
        Page<AdminSearchResp> s = this.adminSrv.listAllAdmin(adminSearchReq);
        return ResponseEntity
                .status(HttpStatus.OK)
                .body(SucRes.ok(s));
    }


}
