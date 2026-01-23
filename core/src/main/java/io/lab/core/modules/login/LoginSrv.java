package io.lab.core.modules.login;

import io.lab.core.modules.admin.AdminMdl;
import io.lab.core.modules.admin.AdminSrv;
import io.lab.core.modules.login.dto.ClaimInfo;
import io.lab.core.modules.exceptions.AppException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.DisabledException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
@Slf4j
public class LoginSrv {

    private final AuthenticationManager authenticationManager;
    private final AdminSrv adminSrv;

    public LoginSrv(
            AuthenticationManager authenticationManager,
            AdminSrv adminSrv) {

        this.authenticationManager = authenticationManager;
        this.adminSrv = adminSrv;
    }

    public ClaimInfo getCredential(String username, String password) {

        try {
            Authentication authentication = UsernamePasswordAuthenticationToken.unauthenticated(username, password);
            this.authenticationManager.authenticate(authentication);
        } catch (UsernameNotFoundException e) {
            throw new AppException("Username not found");
        } catch (BadCredentialsException | DisabledException e) {
            //because spring check disabled exception first , it is wise to blur the message to suer
            throw new AppException("login failed");
        }


        AdminMdl admin = this.adminSrv.findAdminByUsername(username);

        return new ClaimInfo(admin.getId(), admin.getUsername());
    }


}
