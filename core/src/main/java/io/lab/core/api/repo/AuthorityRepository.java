package io.lab.core.api.repo;

import io.lab.core.api.model.AuthorityModel;
import io.lab.core.api.role.RoleModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

import java.util.Collection;

@Repository
public interface AuthorityRepository extends JpaRepository<AuthorityModel, Long>, JpaSpecificationExecutor<AuthorityModel> {
    Collection<AuthorityModel> findPermissionsByRole(RoleModel role);
}
