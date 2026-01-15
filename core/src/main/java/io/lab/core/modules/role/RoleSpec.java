package io.lab.core.modules.role;

import io.lab.core.modules.role.dto.request.RoleSearchReq;
import jakarta.persistence.criteria.Predicate;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.util.StringUtils;

import java.util.ArrayList;
import java.util.List;

public class RoleSpec {

    public static Specification<RoleMdl> searchSpec(RoleSearchReq roleSearchReq) {
        return (root, query, builder) -> {
            List<Predicate> predicates = new ArrayList<>();

            if (StringUtils.hasText(roleSearchReq.getRoleName())) {
                String pattern = "%" + roleSearchReq.getRoleName() + "%";
                predicates.add(builder.like(root.get(RoleMdl_.roleName), pattern));
            }
            if (roleSearchReq.getEnabled() != null) {
                predicates.add(builder.equal(root.get(RoleMdl_.enabled), roleSearchReq.getEnabled()));
            }

            return builder.and(predicates);

        };
    }
}