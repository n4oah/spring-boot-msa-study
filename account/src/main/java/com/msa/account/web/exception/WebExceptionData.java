package com.msa.account.web.exception;

import com.msa.account.exception.ExceptionCode;
import org.springframework.http.HttpStatusCode;

public record WebExceptionData(HttpStatusCode status, String message, ExceptionCode exceptionCode) {}
