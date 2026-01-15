package io.lab.core.modules.permission;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Set;

@Repository
public interface PermRepo extends JpaRepository<PermMdl, Long> {

}
