package com.betul.oms.domain.exception;

import lombok.Getter;

@Getter
public class ValidationException extends OrderManagementSystemException {
    private final String field;
    public ValidationException(String field , String message) {
        super(message);
        this.field = field;
    }
}
