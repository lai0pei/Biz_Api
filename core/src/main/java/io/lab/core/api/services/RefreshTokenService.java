package io.lab.core.api.services;

import io.lab.core.api.admin.AdminRepository;
import io.lab.core.api.exceptions.TokenRefreshException;
import io.lab.core.api.model.RefreshTokenModel;
import io.lab.core.api.repo.RefreshTokenRepository;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.Instant;
import java.util.Optional;
import java.util.UUID;

@Service
public class RefreshTokenService {

    private final RefreshTokenRepository refreshTokenRepository;
    private final AdminRepository adminRepository;
    @Value("${app.jwt.refreshExpirationMs}")
    private Long refreshTokenDurationMs;

    RefreshTokenService(RefreshTokenRepository refreshTokenRepository, AdminRepository adminRepository) {
        this.refreshTokenRepository = refreshTokenRepository;
        this.adminRepository = adminRepository;
    }

    public Optional<RefreshTokenModel> findByToken(String token) {
        return refreshTokenRepository.findByToken(token);
    }

    public RefreshTokenModel createRefreshToken(Long adminId) {
        RefreshTokenModel refreshTokenModel = new RefreshTokenModel();

//        refreshTokenModel.setAdminModel(adminRepository.findById(adminId).orElse(null));
        refreshTokenModel.setExpiryDate(Instant.now().plusMillis(refreshTokenDurationMs));
        refreshTokenModel.setToken(UUID.randomUUID().toString());

        refreshTokenModel = refreshTokenRepository.save(refreshTokenModel);
        return refreshTokenModel;
    }

    public RefreshTokenModel verifyExpiration(RefreshTokenModel token) {
        if (token.getExpiryDate().compareTo(Instant.now()) < 0) {
            refreshTokenRepository.delete(token);
            throw new TokenRefreshException(token.getToken(),
                    "Refresh token was expired. Please make a new signin request");
        }

        return token;
    }


}
