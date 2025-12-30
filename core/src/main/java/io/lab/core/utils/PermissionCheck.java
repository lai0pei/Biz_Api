package io.lab.core.utils;

import org.springframework.stereotype.Component;

@Component(value = "Permission")
public class PermissionCheck {
    public boolean check(String permission) {
//        String authority = module + ":" + action;
//        Collection<? extends GrantedAuthority> authorities = SecurityContextHolder
//                .getContext()
//                .getAuthentication()
//                .getAuthorities();
//        for (GrantedAuthority grantedAuthority : authorities) {
//            if (grantedAuthority.getAuthority().equals(authority)) {
//                return true;
//            }
//        }
        return true;
    }
}
