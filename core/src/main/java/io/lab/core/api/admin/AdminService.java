package io.lab.core.api.admin;


import io.lab.core.api.admin.request.AddDto;
import io.lab.core.api.admin.request.DeleteDto;
import io.lab.core.api.admin.request.SearchDto;
import io.lab.core.api.admin.request.UpdateDto;
import io.lab.core.api.admin.response.SearchResult;
import io.lab.core.api.exceptions.AppException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.math.BigInteger;
import java.time.LocalDateTime;

@Service
@Slf4j
public class AdminService {

    private final AdminRepository adminRepository;
    private final PasswordEncoder passwordEncoder;

    public AdminService(AdminRepository adminRepository, PasswordEncoder passwordEncoder) {
        this.adminRepository = adminRepository;
        this.passwordEncoder = passwordEncoder;
    }

    public long addAdmin(AddDto addDto) throws Exception {
        String username = addDto.username();

        assert this.adminRepository != null;
        System.out.println(adminRepository);

        if (this.adminRepository.existsByUsername(username)) {
            throw new AppException("Username already exists!");
        }
        AdminModel adminModel = new AdminModel();
        adminModel.setUsername(username);
        adminModel.setName(addDto.name());
        adminModel.setPassword(passwordEncoder.encode(addDto.password()));
        adminModel.setSuperAdmin(addDto.isSuperAdmin());
        adminModel.setEnabled(addDto.isEnabled());
        adminModel.setRoleId(addDto.roleId());
        adminModel.setLastLoginTime(LocalDateTime.now());
        AdminModel res = this.adminRepository.save(adminModel);
        return res.getId();
    }

    public void updateAdmin(UpdateDto updateDto) {
        AdminModel adminModel = this.adminRepository.findById(updateDto.id()).orElseThrow(() -> new AppException("Admin not found!"));
        adminModel.setName(updateDto.name());
        adminModel.setSuperAdmin(updateDto.isSuperAdmin());
        adminModel.setEnabled(updateDto.isEnabled());
        adminModel.setRoleId(updateDto.roleId());
    }

    public void deleteAdmin(DeleteDto deleteDto) {
        this.adminRepository.deleteById(deleteDto.getId());
    }

    public AdminModel getAdmin(long id) {
        return this.adminRepository.findById(id).orElseThrow(() -> new AppException("Admin not found!"));
    }

    public Page<SearchResult> listAllAdmin(SearchDto searchDto) {
        Pageable pageable = PageRequest.of(searchDto.getPage(), searchDto.getSize(), Sort.Direction.DESC, "id");
        return this.adminRepository.searchAdminList(searchDto, pageable);
    }
}
