package io.lab.core.api.repo;

import io.lab.core.api.permission.PermissionModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface PermissionRepository extends JpaRepository<PermissionModel, Long> {
    @Query("select p.type from PermissionModel p where p.isEnabled=true")
    Optional<PermissionType> getAllByEnabled();

    interface PermissionType {
        String getType();
    }
}

