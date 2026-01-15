package io.lab.core.config.security;

import io.jsonwebtoken.Claims;
import io.lab.core.modules.admin.securityDetail.AdminDetailService;
import io.lab.core.modules.login.OauthService;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;
import java.nio.file.AccessDeniedException;
import java.util.Optional;
import java.util.UUID;

@Component
@Slf4j
public class JwtAuthenticationFilter extends OncePerRequestFilter {

    private static final String BEARER_PREFIX = "Bearer ";

    private final OauthService oauthService;
    private final AdminDetailService adminDetailService;

    public JwtAuthenticationFilter(
            OauthService oauthService,
            AdminDetailService adminDetailService
    ) {
        this.oauthService = oauthService;
        this.adminDetailService = adminDetailService;
    }
    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
        String bearerToken = extractJwtFromRequest(request);

        if (StringUtils.hasText(bearerToken)) {
            Optional<Claims> claims = oauthService.parseClaim(bearerToken);
            if (claims.isPresent()) {
               Claims claim = claims.get();
               String username = claim.getSubject();
               UUID id = UUID.fromString(claim.getId());
               UUID userId = UUID.fromString(claim.get("userId", String.class));
               // check if token valid
                //check if user valid
                //all from
                try{
                    UserDetails user = adminDetailService.loadUserByUsername(username);
                    Authentication authentication = UsernamePasswordAuthenticationToken.authenticated(user.getUsername(),null,user.getAuthorities());
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
