package com.livenow.slf4jlogbacklab.exception;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class Slf4jRestControllerAdvice {
    
    private Logger logger = LoggerFactory.getLogger(Slf4jRestControllerAdvice.class);
    
    @ExceptionHandler(Exception.class)
    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    public String exception(Exception exception) {
        logger.error("Internal Server Exception: {}", exception.getMessage()); //TODO - 500 internal server 에러는 .error()로
        return exception.getMessage();
    }
    
    @ExceptionHandler(IllegalArgumentException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public String illegalArgumentException(IllegalArgumentException exception) {
        logger.error("IllegalArgumentException: {}", exception.getMessage()); //TODO - unchecked exception이라도, .error()로 로그 남긴다
        return exception.getMessage();
    }
    
    @ExceptionHandler(CommonException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public String notFoundException(CommonException exception) {
        logger.error("CommonException: {}", exception.getMessage());
        return exception.getMessage();
    }
}
