package io.lab.core.modules.role;

import io.lab.core.extension.ExtRepo;
import io.lab.core.modules.role.dto.request.RoleSearchReq;
import io.lab.core.modules.role.dto.response.RoleSearchResp;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface RoleRepo extends ExtRepo<RoleMdl, Long> {
    Optional<RoleMdl> findByRoleName(String name);

    default Page<RoleSearchResp> searchRoleList(RoleSearchReq roleSearchReq, Pageable pageable) {
        Specification<RoleMdl> spec = RoleSpec.searchSpec(roleSearchReq);
        return findBy(spec, q -> q.as(RoleSearchResp.class).page(pageable));
    }
}