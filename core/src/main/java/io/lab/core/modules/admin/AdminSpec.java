package io.lab.core.modules.admin;

import io.lab.core.modules.admin.dto.request.AdminSearchReq;
import jakarta.persistence.criteria.Predicate;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.util.StringUtils;

import java.util.ArrayList;
import java.util.List;


public class AdminSpec {

    public static Specification<AdminMdl> searchSpec(AdminSearchReq searchReq) {
        return (root, query, builder) -> {
            List<Predicate> predicates = new ArrayList<>();

            // Filter: username
            if (StringUtils.hasText(searchReq.getUsername())) {
                String pattern = "%" + searchReq.getUsername() + "%";
                predicates.add(builder.like(root.get(AdminMdl_.username), pattern));
            }
            // Filter: enabled
            if (searchReq.getEnabled() != null) {
                predicates.add(builder.equal(root.get(AdminMdl_.enabled), searchReq.getEnabled()));
            }

            return builder.and(predicates);

        };
    }


}
