package com.msa.account.web.exception;

import com.msa.account.exception.ExceptionCode;
import com.msa.account.exception.ParentException;
import io.jsonwebtoken.ExpiredJwtException;
import io.jsonwebtoken.security.SignatureException;
import jakarta.validation.ValidationException;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class CustomExceptionHandler {
//    @ExceptionHandler({MethodArgumentNotValidException.class})
//    public ResponseEntity<WebExceptionData> validExceptionHandler(
//            MethodArgumentNotValidException ex) {
//
//        WebExceptionData response = new WebExceptionData(
//                HttpStatusCode.valueOf(HttpStatus.BAD_REQUEST.value()),
//                ex.getMessage(),
//                ExceptionCode.VALIDATION_ERROR
//        );
//
//        return new ResponseEntity<>(response, response.status());
//    }

    @ExceptionHandler({ValidationException.class})
    public ResponseEntity<WebExceptionData> exceptionHandle(
            ValidationException ex) {

        WebExceptionData response = new WebExceptionData(
                HttpStatusCode.valueOf(HttpStatus.BAD_REQUEST.value()),
                ex.getMessage(),
                ExceptionCode.VALIDATION_ERROR
        );

        return new ResponseEntity<>(response, response.status());
    }

    @ExceptionHandler({ExpiredJwtException.class})
    public ResponseEntity<WebExceptionData> exceptionHandle(
            ExpiredJwtException ex) {

        WebExceptionData response = new WebExceptionData(
                HttpStatusCode.valueOf(this.getHttpStatusByExceptionCode(ExceptionCode.EXPIRED_JWT).value()),
            "AccessToken 이 만료 되었습니다.",
                ExceptionCode.EXPIRED_JWT
        );

        return new ResponseEntity<>(response, response.status());
    }

    @ExceptionHandler({SignatureException.class})
    public ResponseEntity<WebExceptionData> exceptionHandle(
            SignatureException ex) {

        WebExceptionData response = new WebExceptionData(
                HttpStatusCode.valueOf(this.getHttpStatusByExceptionCode(ExceptionCode.JWT_AUTH_FAIL).value()),
                "인증실패",
                ExceptionCode.JWT_AUTH_FAIL
        );

        return new ResponseEntity<>(response, response.status());
    }

    @ExceptionHandler({ParentException.class})
    public ResponseEntity<WebExceptionData> exceptionHandle(
            ParentException ex) {

        WebExceptionData response = new WebExceptionData(
                HttpStatusCode.valueOf(this.getHttpStatusByExceptionCode(ex.getExceptionCode()).value()),
                ex.getMessage(),
                ex.getExceptionCode()
        );

        return new ResponseEntity<>(response, response.status());
    }

    private HttpStatus getHttpStatusByExceptionCode(ExceptionCode exceptionCode) {
        return switch (exceptionCode) {
            case DUPLICATION -> HttpStatus.CONFLICT;
            case VALIDATION_ERROR -> HttpStatus.BAD_REQUEST;
            case EXPIRED_JWT, JWT_AUTH_FAIL ->  HttpStatus.UNAUTHORIZED;
        };
    }
}
