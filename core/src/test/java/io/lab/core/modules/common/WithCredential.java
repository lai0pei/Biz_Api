package io.lab.core.modules.common;

import io.lab.core.modules.login.dto.request.CredentialReq;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.test.context.bean.override.mockito.MockitoBean;


public class WithCredential {

    protected String Token;
    @MockitoBean
    private PasswordEncoder passwordEncoder;


    void getToken(){
        CredentialReq credentialReq = new CredentialReq(
                "admin",
                passwordEncoder.encode("admin")
        );
    }
}
