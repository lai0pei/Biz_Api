package io.lab.core.api.repo;

import io.lab.core.api.admin.AdminModel;
import io.lab.core.api.model.RefreshTokenModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface RefreshTokenRepository extends JpaRepository<RefreshTokenModel, Long> {
    Optional<RefreshTokenModel> findByToken(String token);

    @Modifying
    int deleteByAdmin(AdminModel admin);
}
