package com.betul.oms.api.exception;

import com.betul.oms.domain.exception.BusinessRuleViolationException;
import com.betul.oms.domain.exception.NotFoundException;
import jakarta.servlet.http.HttpServletRequest;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.time.Instant;
import java.util.List;
    @Slf4j
    @RestControllerAdvice
    public class GlobalExceptionHandler {
        @ExceptionHandler(MethodArgumentNotValidException.class)
        public ResponseEntity<ErrorResponse> handleValidation(
                MethodArgumentNotValidException exception,
                HttpServletRequest request

        ) {
            List<ErrorDetail> details = exception.getBindingResult()
                    .getFieldErrors()
                    .stream()
                    .map(this::toDetail)
                    .toList();

            HttpStatus status = HttpStatus.BAD_REQUEST;

            ErrorResponse body = new ErrorResponse(
                    Instant.now(),
                    status.value(),
                    status.getReasonPhrase(),
                    "Request validation failed",
                    request.getRequestURI(),
                    details
            );

            return ResponseEntity.status(status).body(body);

        }

        @ExceptionHandler(HttpMessageNotReadableException.class)
        public ResponseEntity<ErrorResponse> handleNotReadable(
                HttpMessageNotReadableException exception,
                HttpServletRequest request
        ) {
            String message = "Malformed JSON request";

            Throwable rootCause = exception.getMostSpecificCause();

            List<ErrorDetail> details = List.of();
            if (rootCause instanceof com.fasterxml.jackson.databind.exc.MismatchedInputException mie) {
                message = "JSON type mismatch or invalid value";

                String path = mie.getPath().stream()
                        .map(ref -> ref.getFieldName() != null
                                ? ref.getFieldName()
                                : "[" + ref.getIndex() + "]")
                        .reduce((a, b) -> b.startsWith("[") ? a + b : a + "." + b)
                        .orElse("body");

                details = List.of(new ErrorDetail(path, "Invalid value/type"));
            }
            HttpStatus status = HttpStatus.BAD_REQUEST;

            ErrorResponse body = new ErrorResponse(
                    Instant.now(),
                    status.value(),
                    status.getReasonPhrase(),
                    message,
                    request.getRequestURI(),
                    details
            );
            return ResponseEntity.status(status).body(body);

        }

        @ExceptionHandler(NotFoundException.class)
        public ResponseEntity<ErrorResponse> handleNotFound(
                NotFoundException exception,
                HttpServletRequest request
        ) {
            HttpStatus status = HttpStatus.NOT_FOUND;

            ErrorResponse body = new ErrorResponse(
                    Instant.now(),
                    status.value(),
                    status.getReasonPhrase(),
                    exception.getMessage(),
                    request.getRequestURI(),
                    List.of()
            );

            return ResponseEntity.status(status).body(body);
        }

        @ExceptionHandler(BusinessRuleViolationException.class)
        public ResponseEntity<ErrorResponse> handleBusinessRuleViolation(
                BusinessRuleViolationException exception,
                HttpServletRequest request
        ) {
            HttpStatus status = HttpStatus.CONFLICT;

            ErrorResponse body = new ErrorResponse(
                    Instant.now(),
                    status.value(),
                    status.getReasonPhrase(),
                    exception.getMessage(),
                    request.getRequestURI(),
                    List.of()
            );

            return ResponseEntity.status(status).body(body);
        }

        @ExceptionHandler(Exception.class)
        public ResponseEntity<ErrorResponse> handleGenericException(
                Exception exception,
                HttpServletRequest request
        ) {

            log.error("Unhandled exception {} {}", request.getMethod(), request.getRequestURI(), exception);
            HttpStatus status = HttpStatus.INTERNAL_SERVER_ERROR;
            ErrorResponse body = new ErrorResponse(
                    Instant.now(),
                    status.value(),
                    status.getReasonPhrase(),
                    "Unexpected error occurred",
                    request.getRequestURI(),
                    List.of()
            );
            return ResponseEntity.status(status).body(body);
        }

        private ErrorDetail toDetail(FieldError fieldError) {
            return new ErrorDetail(fieldError.getField(), fieldError.getDefaultMessage());
        }
    }


