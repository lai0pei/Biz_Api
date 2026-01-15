package io.lab.core.modules.menu;

import io.lab.core.modules.admin.dto.request.AdminSearchReq;
import io.lab.core.modules.admin.dto.response.AdminSearchResp;
import io.lab.core.modules.menu.dto.request.MenuSearchReq;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Slf4j
public class MenuSrv {
    private final MenuRepo menuRepo;

    public MenuSrv(MenuRepo menuRepo){
        this.menuRepo = menuRepo;
    }

    public void createMenu(){

//        menuRepo.save();
    }

    public void updateMenu(){

    }

    public void deleteMenu(){

    }

    @Transactional(readOnly = true)
    public Page<MenuMdl> listMenu(MenuSearchReq menuSearchReq){
        Pageable pageable = PageRequest.of(menuSearchReq.getPage(), menuSearchReq.getSize(), Sort.Direction.DESC, "id");
        return this.menuRepo.listAllMenu(pageable);
    }

}
