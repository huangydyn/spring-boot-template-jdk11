package com.template.representation.advice;


import com.template.exception.BadResponseException;
import lombok.NoArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import static com.google.common.collect.Lists.newArrayList;
import static java.lang.String.format;

@Slf4j
@NoArgsConstructor
@RestControllerAdvice
public class ControllerAdvice extends ResponseEntityExceptionHandler {

    private static final String COMMON_ERROR_FORMAT = "Error occurs ex: {}, message: {}, cause: {}, stack_trace: {}";

    @ExceptionHandler(BadResponseException.class)
    @ResponseBody
    public ResponseEntity<ErrorResponse> handleBadResponseException(BadResponseException ex) {
        log.error(COMMON_ERROR_FORMAT, ex.getClass(), ex.getMessage(), ex.getCause(), ex.getStackTrace());
        return ResponseEntity
                .status(ex.getHttpStatus())
                .body(ErrorResponse.of(ex.getHttpStatus().value(), ex.getMessage(), ex.getDetailErrors()));
    }

    @Override
    @ResponseBody
    protected ResponseEntity<Object> handleMethodArgumentNotValid(MethodArgumentNotValidException ex,
                                                                  HttpHeaders headers,
                                                                  HttpStatus status,
                                                                  WebRequest request) {
        String failedFields = ex.getBindingResult().getFieldErrors().stream()
                .map(FieldError::getField)
                .reduce((f1, f2) -> f1 + " , " + f2)
                .orElse("");

        return ResponseEntity.status(HttpStatus.UNPROCESSABLE_ENTITY)
                .body(ErrorResponse.of(HttpStatus.UNPROCESSABLE_ENTITY.value(),
                        format("Api Request Error: fields %s require not empty", failedFields), newArrayList()));
    }

    @ExceptionHandler(Exception.class)
    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    @ResponseBody
    public ErrorResponse handleException(Exception ex) {
        log.error(COMMON_ERROR_FORMAT, ex.getClass(), ex.getMessage(), ex.getCause(), ex.getStackTrace());
        return ErrorResponse.of(HttpStatus.INTERNAL_SERVER_ERROR.value(), ex.getMessage(), newArrayList());
    }
}

