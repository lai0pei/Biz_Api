package io.lab.core.modules.admin;

import io.lab.core.extension.ExtRepo;
import io.lab.core.modules.admin.dto.request.AdminSearchReq;
import io.lab.core.modules.admin.dto.response.AdminSearchResp;
import jakarta.annotation.Nonnull;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.stereotype.Repository;

import java.util.Optional;
import java.util.UUID;

@Repository
public interface AdminRepo extends ExtRepo<AdminMdl, UUID> {

    Optional<AdminMdl> findByUsername(String username);

    Boolean existsByUsername(String username);

    void deleteById(@Nonnull UUID id);

    @EntityGraph
    default Page<AdminSearchResp> listAllAdmin(AdminSearchReq adminSearchReq, Pageable pageable) {
        Specification<AdminMdl> spec = AdminSpec.searchSpec(adminSearchReq);
        return findBy(spec, q -> q.as(AdminSearchResp.class).page(pageable));
    }

}


















