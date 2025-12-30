package io.lab.core.api.common;

import org.springframework.http.HttpStatus;

public record RespDto<T>(int code, String msg, T data) {

    public static <T> RespDto<T> success(T data) {
        return new RespDto<T>(HttpStatus.OK.value(), "OK", data);
    }

    public static <T> RespDto<T> success(T data, String msg) {
        return new RespDto<T>(HttpStatus.OK.value(), msg, null);
    }

    public static <T> RespDto<T> success(T data, String msg, int code) {
        return new RespDto<T>(code, msg, null);
    }


    public static <T> RespDto<T> error(int code, String msg) {
        return new RespDto<T>(code, msg, null);
    }


    public static <T> RespDto<T> error(String msg) {
        return new RespDto<T>(HttpStatus.INTERNAL_SERVER_ERROR.value(), msg, null);
    }


}
