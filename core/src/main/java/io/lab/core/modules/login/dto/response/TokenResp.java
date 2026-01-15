package io.lab.core.modules.login.dto.response;

public record TokenResp(
        String accessToken,
        String refreshToken
) {
}
