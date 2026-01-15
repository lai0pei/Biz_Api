package io.lab.core.modules.repo;

import io.lab.core.modules.admin.AdminMdl;
import io.lab.core.modules.model.RefreshTokenModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface RefreshTokenRepository extends JpaRepository<RefreshTokenModel, Long> {
    Optional<RefreshTokenModel> findByToken(String token);

    @Modifying
    int deleteByAdmin(AdminMdl admin);
}
