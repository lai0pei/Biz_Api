package io.lab.core.modules.menu;

import io.lab.core.common.SucRes;
import io.lab.core.modules.menu.dto.request.MenuSearchReq;
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
@RequestMapping("/modules/menu")
@Tag(name = "Admin Management")
@SecurityRequirement(name = "Bearer-token")
public class MenuCtr {

    private final MenuSrv menuSrv;

    MenuCtr(MenuSrv menuSrv) {
        this.menuSrv = menuSrv;
    }


    @Operation(summary = "Fetch list of admin plus filter search")
    @PreAuthorize("@Permission.check('MENU_LIST')")
    @PostMapping("/list")
    public ResponseEntity<?> list(@Valid @RequestBody MenuSearchReq menuSearchReq) {
        Page<MenuMdl> s = this.menuSrv.listMenu(menuSearchReq);
        return ResponseEntity
                .status(HttpStatus.OK)
                .body(SucRes.ok(s));
    }


}
