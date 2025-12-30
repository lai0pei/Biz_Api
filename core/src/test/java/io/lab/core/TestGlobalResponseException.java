package io.lab.core;

import io.lab.core.api.common.RespDto;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class TestGlobalResponseException {
    @ExceptionHandler(Exception.class)
    public <T> ResponseEntity<RespDto<T>> handle(Exception e) {
        return ResponseEntity
                .status(HttpStatus.INTERNAL_SERVER_ERROR)
                .body(RespDto.error(e.getMessage()));
    }
}
