package io.lab.core.api.admin;

import io.lab.core.api.admin.request.SearchDto;
import jakarta.persistence.criteria.Predicate;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.util.StringUtils;

import java.util.ArrayList;
import java.util.List;


public class AdminSpecification {

    public static Specification<AdminModel> searchSpec(SearchDto criteria) {
        return (root, query, criteriaBuilder) -> {
            List<Predicate> predicates = new ArrayList<>();

            assert query != null;

            // Filter: username
//            if (StringUtils.hasText(criteria.getUsername())) {
//                String pattern = "%" + criteria.getUsername() + "%";
//                predicates.add(criteriaBuilder.like(root.get(AdminModel_.username), pattern));
//            }
            // Filter: isEnabled
            if (criteria.getIsEnabled() != null) {
                predicates.add(criteriaBuilder.equal(root.get("isEnabled"), criteria.getIsEnabled()));
            }

            // Filter: lastLoginTime range
            if (criteria.getLastLoginTime() != null) {
                predicates.add(criteriaBuilder.greaterThanOrEqualTo(root.get("lastLoginTime"), criteria.getLastLoginTime()));
            }

            return criteriaBuilder.and(predicates.toArray(new Predicate[0]));

        };
    }

}
