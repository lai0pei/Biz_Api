package io.lab.core.common;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Builder;
import org.springframework.http.HttpStatus;

import java.time.LocalDateTime;

@Schema(description = "Error response")
@Builder
public record ErrRes<T>(
        @Schema(description = "Error status of the operation", example = "Operation Timestamp")
        LocalDateTime timestamp,
        @Schema(description = "Error status of the operation", example = "500")
        int status,
        @Schema(description = "Error reason of the operation", example = "Exception Message")
        String message,
        @Schema(description = "Error status of the operation", example = "[]")
        T error,
        @Schema(description = "Error modules path of the operation", example = "/modules/current")
        String path
) {

    public static <T> ErrRes<T> error(String message) {
        var timestamp = LocalDateTime.now();
        return new ErrRes<T>(timestamp, HttpStatus.INTERNAL_SERVER_ERROR.value(), message, null, null);
    }
}
