package io.lab.core.modules.services;

import io.lab.core.modules.admin.AdminRepo;
import io.lab.core.modules.exceptions.TokenRefreshException;
import io.lab.core.modules.model.RefreshTokenModel;
import io.lab.core.modules.repo.RefreshTokenRepository;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.time.Instant;
import java.util.Optional;
import java.util.UUID;

@Service
public class RefreshTokenService {

    private final RefreshTokenRepository refreshTokenRepository;
    private final AdminRepo adminRepo;
    @Value("${app.jwt.refreshExpirationMs}")
    private Long refreshTokenDurationMs;

    RefreshTokenService(RefreshTokenRepository refreshTokenRepository, AdminRepo adminRepo) {
        this.refreshTokenRepository = refreshTokenRepository;
        this.adminRepo = adminRepo;
    }

    public Optional<RefreshTokenModel> findByToken(String token) {
        return refreshTokenRepository.findByToken(token);
    }

    public RefreshTokenModel createRefreshToken(Long adminId) {
        RefreshTokenModel refreshTokenModel = new RefreshTokenModel();

//        refreshTokenModel.setAdminModel(adminRepo.findById(adminId).orElse(null));
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
