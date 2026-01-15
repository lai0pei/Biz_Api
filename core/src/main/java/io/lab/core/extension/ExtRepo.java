package io.lab.core.extension;


import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.repository.NoRepositoryBean;

@NoRepositoryBean
public interface ExtRepo<Mdl, Rtn> extends JpaRepository<Mdl, Rtn>, JpaSpecificationExecutor<Mdl> {
}
