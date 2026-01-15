package io.lab.core.modules.login.dto;

import java.util.UUID;

public record ClaimInfo(
        UUID id,
        String username
) {
    public static ClaimInfo empty(){
        return new ClaimInfo(UUID.fromString("00000000-0000-0000-0000-000000000000"), "");
    }
}
