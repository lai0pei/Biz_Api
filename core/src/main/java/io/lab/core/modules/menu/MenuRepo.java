package io.lab.core.modules.menu;

import io.lab.core.extension.ExtRepo;
import io.lab.core.modules.admin.AdminMdl;
import io.lab.core.modules.admin.AdminSpec;
import io.lab.core.modules.admin.dto.request.AdminSearchReq;
import io.lab.core.modules.admin.dto.response.AdminSearchResp;
import io.lab.core.modules.menu.dto.request.MenuSearchReq;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface MenuRepo extends ExtRepo<MenuMdl, Long> {
    MenuMdl findByMenuSig(MenuSig sig);

    default Page<MenuMdl> listAllMenu( Pageable pageable) {
        return findAll(pageable);
    }

}
