package io.lab.core.aspect;

import io.lab.core.api.common.RespDto;
import io.lab.core.api.exceptions.AppException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.Profile;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;


@RestControllerAdvice(basePackages = "io.lab.core.api")
@Slf4j
public class GlobalResponseException {

    @ExceptionHandler(Exception.class)
    public <T>ResponseEntity<RespDto<T>> handle(Exception e){
        String message = "操作失败";
        if(e instanceof AppException){
          message = e.getMessage();
//          log.error(e.getMessage());
        }
        return ResponseEntity
                .status(HttpStatus.INTERNAL_SERVER_ERROR)
                .body(RespDto.error(message));
    }
}

