package io.lab.core.modules.login;


import io.lab.core.common.SucRes;
import io.lab.core.modules.login.dto.request.CredentialReq;
import io.lab.core.modules.login.dto.response.TokenResp;
import io.lab.core.modules.login.dto.ClaimInfo;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/modules/auth")
@Tag(name = "User Login")
public class LoginCtr {

    private final OauthSrv oauthSrv;
    private final LoginSrv service;
    public LoginCtr(LoginSrv service, OauthSrv oauthSrv) {
        this.oauthSrv = oauthSrv;
        this.service = service;
    }

    @Operation(summary = "username and password login" )
    @PostMapping("/login")
    public ResponseEntity<SucRes<TokenResp>> login(@Valid @RequestBody CredentialReq credentialReq) {
        ClaimInfo credential = this.service.getCredential(
                credentialReq.username(),
                credentialReq.password());

        TokenResp tokenResp = new TokenResp(
                oauthSrv.createAccessToken(credential.id(), credential.username()),
                oauthSrv.createRefreshToken(credential.id(), credential.username())
        );


        return ResponseEntity
                .status(HttpStatus.OK)
                .body(SucRes.ok(tokenResp));
    }

//    @PostMapping("/refresh")
//    public RespDto<TokenResp> refresh(@Valid @RequestBody RefreshTokenReq tokenRefresh) {
//        try{
//           Claims claims =  oauthService.parseClaim(tokenRefresh.refreshToken());
//           //black list check
//            //username and id check with current admin from cache
//            //generate new token
//
//        }catch (Exception e){
//            return RespDto.error("Invalid Token");
//        }
//
//    }
}
