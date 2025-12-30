package io.lab.core.api.admin;

import io.lab.core.api.admin.request.AddDto;
import io.lab.core.api.admin.request.DeleteDto;
import io.lab.core.api.admin.request.SearchDto;
import io.lab.core.api.admin.request.UpdateDto;
import io.lab.core.api.admin.response.SearchResult;
import io.lab.core.api.common.RespDto;
import jakarta.validation.Valid;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RestController
@RequestMapping("/api/admin")
public class AdminController {

    private final AdminService adminService;

    AdminController(AdminService adminService) {
        this.adminService = adminService;
    }

    @PreAuthorize("@Permission.check('admin:add')")
    @PostMapping("/add")
    public RespDto<Boolean> add(@Valid @RequestBody AddDto addDto) throws Exception {
        this.adminService.addAdmin(addDto);
        return RespDto.success(true);
    }

    @PreAuthorize("@Permission.check('admin:update')")
    @PostMapping("/update")
    public RespDto<Boolean> update(@Valid @RequestBody UpdateDto updateDto) {
        this.adminService.updateAdmin(updateDto);
        return RespDto.success(true);
    }


    @PreAuthorize("@Permission.check('admin:delete')")
    @PostMapping("/delete")
    public RespDto<String> delete(@Valid @RequestBody DeleteDto deleteDto) throws Exception {
        this.adminService.deleteAdmin(deleteDto);
        return RespDto.success("Success");
    }


    @PreAuthorize("@Permission.check('admin:list')")
    @PostMapping("/list")
    public RespDto<Page<SearchResult>> list(@Valid @RequestBody SearchDto searchDto) {
        Page<SearchResult> s = this.adminService.listAllAdmin(searchDto);
        return RespDto.success(s);
    }
}
