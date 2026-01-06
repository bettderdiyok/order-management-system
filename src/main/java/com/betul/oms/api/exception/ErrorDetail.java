package com.betul.oms.api.exception;

public record ErrorDetail(
        String field,
        String message
) {}
