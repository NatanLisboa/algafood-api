package com.lisboaworks.algafood.api.exceptionhandler;

import com.lisboaworks.algafood.domain.exception.BusinessRuleException;
import com.lisboaworks.algafood.domain.exception.EntityAlreadyInUseException;
import com.lisboaworks.algafood.domain.exception.EntityNotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.HttpMediaTypeNotSupportedException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import java.time.LocalDateTime;

@ControllerAdvice
public class ApiExceptionHandler {

    @ExceptionHandler(EntityNotFoundException.class)
    public ResponseEntity<?> handleEntityNotFoundException(EntityNotFoundException e) {
        ApiException apiException = ApiException.builder()
                .datetime(LocalDateTime.now())
                .message(e.getMessage())
                .build();

        return ResponseEntity.status(HttpStatus.NOT_FOUND)
                .body(apiException);
    }

    @ExceptionHandler(BusinessRuleException.class)
    public ResponseEntity<?> handleBusinessRuleException(BusinessRuleException e) {
        ApiException apiException = ApiException.builder()
                .datetime(LocalDateTime.now())
                .message(e.getMessage())
                .build();

        return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                .body(apiException);
    }

    @ExceptionHandler(HttpMediaTypeNotSupportedException.class)
    public ResponseEntity<?> handleHttpMediaTypeNotSupportedException() {
        ApiException apiException = ApiException.builder()
                .datetime(LocalDateTime.now())
                .message("This media type is not accepted. The API supports only JSON media type")
                .build();

        return ResponseEntity.status(HttpStatus.UNSUPPORTED_MEDIA_TYPE)
                .body(apiException);
    }

    @ExceptionHandler(EntityAlreadyInUseException.class)
    public ResponseEntity<?> handleEntityAlreadyInUseException(EntityAlreadyInUseException e) {
        ApiException apiException = ApiException.builder()
                .datetime(LocalDateTime.now())
                .message(e.getMessage())
                .build();

        return ResponseEntity.status(HttpStatus.CONFLICT)
                .body(apiException);
    }

}
