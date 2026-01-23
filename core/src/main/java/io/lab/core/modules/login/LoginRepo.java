package io.lab.core.modules.login;


import io.lab.core.modules.admin.AdminMdl;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;
import java.util.UUID;

@Repository
public interface LoginRepo extends JpaRepository<AdminMdl, UUID> {
    Optional<AdminMdl> findByUsernameAndPassword(String username, String password);
}
