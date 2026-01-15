package io.lab.core.utils;

import io.lab.core.modules.permission.PermType;
import lombok.extern.slf4j.Slf4j;
import org.jspecify.annotations.NonNull;
import org.jspecify.annotations.Nullable;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;

import java.util.Collection;
import java.util.Objects;

@Component(value = "Permission")
@Slf4j
public class PermissionCheck {
    public boolean check(@Nullable String permission) {
        if(permission == null){
            log.error("Permission check is null");
            return false;
        }
        String authority = PermType.valueOf(permission).toString();
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();


        Collection<? extends GrantedAuthority> authorities = authentication.getAuthorities();
        if(authorities.isEmpty()){
            return false;
        }
        for (GrantedAuthority grantedAuthority : authorities) {
            if (Objects.equals(grantedAuthority.getAuthority(), authority)) {
                return true;
            }
        }
        log.warn("Permission denied. Required: {}, Found: {}", authority, authorities);
        return false;
    }
}
