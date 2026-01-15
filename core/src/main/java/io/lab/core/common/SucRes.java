package io.lab.core.common;

import io.swagger.v3.oas.annotations.media.Schema;
import org.springframework.http.HttpStatus;


@Schema(description = "Success response")
public record SucRes<T>(
        @Schema(description = "Success status of the operation", example = "200")
        int status,
        @Schema(description = "Success status of the operation", example = "success")
        String message,
        @Schema(description = "Success status of the operation", example = "[]")
        T data
) {

    @Schema(description = "Response data payload")
    public static <T> SucRes<T> ok(T data) { return new SucRes<T>(HttpStatus.OK.value(), "OK", data); }

    @Schema(description = "Response empty payload")
    public static  SucRes<Boolean> empty() { return new SucRes<Boolean>(HttpStatus.OK.value(), "OK", true); }

}


//public record SucRes<T>(
//        int status,
//        String message,
//        T data
//) {
//
//    public static <T> SucRes<T> ok(T data) { return new SucRes<>(HttpStatus.OK.value(), "OK", data); }
//
//    public static SucRes<Boolean> empty() { return new SucRes<>(HttpStatus.OK.value(), "OK", true); }
//
//}