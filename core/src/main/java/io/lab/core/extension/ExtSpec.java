package io.lab.core.extension;

import jakarta.persistence.criteria.Predicate;
import org.springframework.data.jpa.domain.Specification;

import java.util.UUID;

public class ExtSpec {

    public static <Mdl extends OnrMdl> Specification<Mdl> hasOwner(UUID ownerId){
        return (root, query, builder) -> {
            Predicate p = builder.equal(root.get(OnrMdl_.OWNER_ID), ownerId);

            return  builder.and(p);
        };
    }
}
