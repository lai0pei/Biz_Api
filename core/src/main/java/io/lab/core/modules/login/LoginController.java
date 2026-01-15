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
@RequestMapping("/modules/login")
@Tag(name = "User Login")
public class LoginController {

    private final OauthService oauthService;
    private final LoginService service;
    public LoginController(LoginService service, OauthService oauthService) {
        this.oauthService = oauthService;
        this.service = service;
    }

    @Operation(summary = "username and password login" )
    @PostMapping("/login")
    public ResponseEntity<SucRes<TokenResp>> login(@Valid @RequestBody CredentialReq credentialReq) {
        ClaimInfo credential = this.service.getCredential(
                credentialReq.username(),
                credentialReq.password());

        TokenResp tokenResp = new TokenResp(
                oauthService.createAccessToken(credential.id(), credential.username()),
                oauthService.createRefreshToken(credential.id(), credential.username())
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
