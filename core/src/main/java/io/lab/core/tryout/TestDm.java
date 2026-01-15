package io.lab.core.tryout;

import io.lab.core.modules.admin.AdminSrv;
import io.lab.core.modules.admin.dto.request.AdminCreateReq;
import io.lab.core.modules.login.OauthService;
import io.lab.core.common.SucRes;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@Tag(name = "Rest Manual Test")
@Slf4j
public class TestDm {

    private final OauthService oauthService;
    private final AdminSrv adminSrv;

    public TestDm(
            OauthService oauthService,
            AdminSrv adminSrv) {
        this.oauthService = oauthService;
        this.adminSrv = adminSrv;
    }

    @PostMapping("modules/login/adminCreateReq")
    public ResponseEntity<?> add(@Valid @RequestBody AdminCreateReq adminCreateReq) throws Exception {
        this.adminSrv.createAdmin(adminCreateReq);
        return ResponseEntity
                .status(HttpStatus.OK)
                .body(SucRes.empty());
    }

    @PostMapping("/modules/auth2/test")
    @SecurityRequirement(name = "Bearer-token")
    public ResponseEntity<String> test(@AuthenticationPrincipal String admin) {
        log.info(admin);
        return ResponseEntity.ok().body(admin);
    }


}
