package com.msa.account.web.exception;

import com.msa.account.exception.ExceptionCode;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class CustomExceptionHandler {
    @ExceptionHandler({MethodArgumentNotValidException.class})
    public ResponseEntity<WebExceptionData> validException(
            MethodArgumentNotValidException ex) {

        WebExceptionData response = new WebExceptionData(HttpStatusCode.valueOf(HttpStatus.CONFLICT.value()), ex.getMessage(), ExceptionCode.VALIDATION_ERROR);

        return new ResponseEntity<>(response, response.status());
    }
}
