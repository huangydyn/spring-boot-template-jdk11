package com.template.representation.advice;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ErrorResponse {

    private String errorCode;

    private String message;

    private List<String> detailErrors;

    public static ErrorResponse of(int errorCode, String message, List<String> detailErrors) {
        return ErrorResponse.builder()
                .errorCode(String.valueOf(errorCode))
                .message(message)
                .detailErrors(detailErrors)
                .build();
    }
}
