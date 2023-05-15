package com.lisboaworks.algafood.api.exceptionhandler;

import static com.lisboaworks.algafood.api.exceptionhandler.ApiException.ApiExceptionBuilder;
import com.lisboaworks.algafood.domain.exception.BusinessRuleException;
import com.lisboaworks.algafood.domain.exception.EntityAlreadyInUseException;
import com.lisboaworks.algafood.domain.exception.EntityNotFoundException;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.HttpMediaTypeNotSupportedException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import java.time.LocalDateTime;
import java.util.Objects;

@ControllerAdvice
public class ApiExceptionHandler extends ResponseEntityExceptionHandler {

    @ExceptionHandler(EntityNotFoundException.class)
    public ResponseEntity<?> handleEntityNotFoundException(EntityNotFoundException e, WebRequest request /* Automatically injected by Spring */) {
        HttpStatus status = HttpStatus.NOT_FOUND;
        ApiExceptionType apiExceptionType = ApiExceptionType.ENTITY_NOT_FOUND;
        String detail = e.getMessage();

        ApiException entityNotFoundException = createApiExceptionBuilder(status, apiExceptionType, detail)
                .build();

        return handleExceptionInternal(e, entityNotFoundException, new HttpHeaders(), status, request);
    }

    @ExceptionHandler(BusinessRuleException.class)
    public ResponseEntity<?> handleBusinessRuleException(BusinessRuleException e, WebRequest request /* Automatically injected by Spring */) {
        HttpStatus status = HttpStatus.BAD_REQUEST;
        ApiExceptionType apiExceptionType = ApiExceptionType.BUSINESS_RULE_ERROR;
        String detail = e.getMessage();

        ApiException businessRuleException = createApiExceptionBuilder(status, apiExceptionType, detail)
                .build();

        return handleExceptionInternal(e, businessRuleException, new HttpHeaders(), status, request);
    }

    @ExceptionHandler(EntityAlreadyInUseException.class)
    public ResponseEntity<?> handleEntityAlreadyInUseException(EntityAlreadyInUseException e, WebRequest request /* Automatically injected by Spring */ ) {
        HttpStatus status = HttpStatus.CONFLICT;
        ApiExceptionType apiExceptionType = ApiExceptionType.ENTITY_ALREADY_IN_USE;
        String detail = e.getMessage();

        ApiException entityAlreadyInUseException = createApiExceptionBuilder(status, apiExceptionType, detail)
                .build();

        return handleExceptionInternal(e, entityAlreadyInUseException, new HttpHeaders(), status, request);
    }

    @Override
    protected ResponseEntity<Object> handleExceptionInternal(Exception ex, Object body, HttpHeaders headers, HttpStatus status, WebRequest request) {

        if (Objects.isNull(body)) {
            body = ApiException.builder()
                    .title(status.getReasonPhrase())
                    .status(status.value())
                    .build();
        } else if (body instanceof String) {
            body = ApiException.builder()
                    .title((String) body)
                    .status(status.value())
                    .build();
        }

        return super.handleExceptionInternal(ex, body, headers, status, request);
    }

    private ApiExceptionBuilder createApiExceptionBuilder(HttpStatus status,
            ApiExceptionType apiExceptionType, String detail) {
        return ApiException.builder()
                .status(status.value())
                .type(apiExceptionType.getUri())
                .title(apiExceptionType.getTitle())
                .detail(detail);
    }
}
