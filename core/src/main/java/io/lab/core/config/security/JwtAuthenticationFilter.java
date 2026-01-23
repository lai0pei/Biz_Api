package io.lab.core.config.security;

import io.jsonwebtoken.Claims;
import io.lab.core.modules.admin.AdminSrv;
import io.lab.core.modules.admin.dto.projection.AdminDetailDto;
import io.lab.core.modules.login.OauthSrv;
import io.lab.core.modules.role.RoleSrv;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;
import java.nio.file.AccessDeniedException;
import java.util.Collection;
import java.util.Optional;
import java.util.UUID;

@Component
@Slf4j
public class JwtAuthenticationFilter extends OncePerRequestFilter {

    private static final String BEARER_PREFIX = "Bearer ";

    private final OauthSrv oauthSrv;
    private final AdminSrv adminSrv;
    private final RoleSrv roleSrv;

    public JwtAuthenticationFilter(
            OauthSrv oauthSrv,
            AdminSrv adminSrv,
            RoleSrv roleSrv
    ) {
        this.oauthSrv = oauthSrv;
        this.adminSrv = adminSrv;
        this.roleSrv = roleSrv;
    }
    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
        String bearerToken = extractJwtFromRequest(request);

        if (StringUtils.hasText(bearerToken)) {

            Optional<Claims> claims = oauthSrv.parseClaim(bearerToken);
            if (claims.isPresent()) {
               Claims claim = claims.get();
               String username = claim.getSubject();
               UUID id = UUID.fromString(claim.getId());
               UUID userId = UUID.fromString(claim.get("userId", String.class));
               // check if token valid
                //check if user valid
                //all from
                try{
                    //by cached methods
                    AdminDetailDto user = adminSrv.findAdminById(id);
                    //by cached methods
                    Collection<? extends GrantedAuthority> authorities = roleSrv.getAuthoritiesByRoleId(user.role().getId());
                    Authentication authentication = UsernamePasswordAuthenticationToken.authenticated(user.username(),null, authorities);
                    SecurityContextHolder.getContext().setAuthentication(authentication);

                } catch (Exception e) {
                    log.error(e.getMessage());
                  throw new AccessDeniedException("Access denied");
                }
            }

        }
        filterChain.doFilter(request, response);

    }

    private String extractJwtFromRequest(HttpServletRequest request) {
        String bearerToken = request.getHeader("Authorization");

        if (StringUtils.hasText(bearerToken) && bearerToken.startsWith(BEARER_PREFIX)) {
            return bearerToken.substring(BEARER_PREFIX.length());
        }

        return null;
    }


}
