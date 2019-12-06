package com.template.exception;

import lombok.Getter;
import org.springframework.http.HttpStatus;

import java.util.List;

import static com.google.common.collect.Lists.newArrayList;

/**
 * project standard exception
 */
@Getter
public class BadResponseException extends RuntimeException {

    private static final long serialVersionUID = 1296923009039947638L;

    private HttpStatus httpStatus;

    private String message;

    private List<String> detailErrors;

    public BadResponseException(HttpStatus httpStatus, String message) {
        super();
        this.httpStatus = httpStatus;
        this.message = message;
        this.detailErrors = newArrayList();
    }

    public BadResponseException(HttpStatus httpStatus, String message, List<String> detailErrors) {
        super();
        this.httpStatus = httpStatus;
        this.message = message;
        this.detailErrors = detailErrors;
    }
}
