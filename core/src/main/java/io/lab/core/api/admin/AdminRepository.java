package io.lab.core.api.admin;

import io.lab.core.api.admin.request.SearchDto;
import io.lab.core.api.admin.response.SearchResult;
import jakarta.annotation.Nonnull;
import org.hibernate.annotations.processing.CheckHQL;
import org.hibernate.annotations.processing.Find;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface AdminRepository extends JpaRepository<AdminModel, Long>, JpaSpecificationExecutor<AdminModel> {

    Optional<AdminModel> findByUsername(String username);

    Boolean existsByUsername(String username);


    void deleteById(@Nonnull Long id);

    default Page<SearchResult> searchAdminList(SearchDto searchDto, Pageable pageable) {
        Specification<AdminModel> spec = AdminSpecification.searchSpec(searchDto);
        return findBy(spec, q -> q.as(SearchResult.class).page(pageable));
    }

}
