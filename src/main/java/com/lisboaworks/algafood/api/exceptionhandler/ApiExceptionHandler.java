package com.lisboaworks.algafood.api.exceptionhandler;

import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.exc.InvalidFormatException;
import com.fasterxml.jackson.databind.exc.PropertyBindingException;
import com.lisboaworks.algafood.core.validation.ConstraintValidationException;
import com.lisboaworks.algafood.domain.exception.BusinessRuleException;
import com.lisboaworks.algafood.domain.exception.EntityAlreadyInUseException;
import com.lisboaworks.algafood.domain.exception.EntityNotFoundException;
import lombok.AllArgsConstructor;
import org.apache.commons.lang3.exception.ExceptionUtils;
import org.springframework.beans.TypeMismatchException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.method.annotation.MethodArgumentTypeMismatchException;
import org.springframework.web.servlet.NoHandlerFoundException;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import java.time.OffsetDateTime;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

@ControllerAdvice
@AllArgsConstructor
public class ApiExceptionHandler extends ResponseEntityExceptionHandler {

    public static final String FINAL_USER_GENERIC_ERROR_MESSAGE = "An unexpected internal system error has occurred. " +
            "Try again and if the problem persists, contact the system administrator.";

    private final MessageSource messageSource;

    @ExceptionHandler(Exception.class)
    public ResponseEntity<?> handleUncaughtException(Exception ex, WebRequest request) {
        ex.printStackTrace();

        HttpStatus status = HttpStatus.INTERNAL_SERVER_ERROR;
        ApiExceptionType apiExceptionType = ApiExceptionType.SYSTEM_ERROR;

        ApiException applicationException = createApiExceptionBuilder(status, apiExceptionType, FINAL_USER_GENERIC_ERROR_MESSAGE)
                .userMessage(FINAL_USER_GENERIC_ERROR_MESSAGE)
                .build();

        return this.handleExceptionInternal(ex, applicationException, new HttpHeaders(), status, request);
    }

    @ExceptionHandler(EntityNotFoundException.class)
    public ResponseEntity<?> handleEntityNotFoundException(EntityNotFoundException ex, WebRequest request /* Automatically injected by Spring */) {
        HttpStatus status = HttpStatus.NOT_FOUND;
        ApiExceptionType apiExceptionType = ApiExceptionType.RESOURCE_NOT_FOUND;
        String detail = ex.getMessage();

        ApiException entityNotFoundException = createApiExceptionBuilder(status, apiExceptionType, detail)
                .userMessage(detail)
                .build();

        return this.handleExceptionInternal(ex, entityNotFoundException, new HttpHeaders(), status, request);
    }

    @ExceptionHandler(BusinessRuleException.class)
    public ResponseEntity<?> handleBusinessRuleException(BusinessRuleException ex, WebRequest request /* Automatically injected by Spring */) {
        HttpStatus status = HttpStatus.BAD_REQUEST;
        ApiExceptionType apiExceptionType = ApiExceptionType.BUSINESS_RULE_ERROR;
        String detail = ex.getMessage();

        ApiException businessRuleException = createApiExceptionBuilder(status, apiExceptionType, detail)
                .userMessage(detail)
                .build();

        return this.handleExceptionInternal(ex, businessRuleException, new HttpHeaders(), status, request);
    }

    @ExceptionHandler(EntityAlreadyInUseException.class)
    public ResponseEntity<?> handleEntityAlreadyInUseException(EntityAlreadyInUseException ex, WebRequest request /* Automatically injected by Spring */ ) {
        HttpStatus status = HttpStatus.CONFLICT;
        ApiExceptionType apiExceptionType = ApiExceptionType.ENTITY_ALREADY_IN_USE;
        String detail = ex.getMessage();

        ApiException entityAlreadyInUseException = createApiExceptionBuilder(status, apiExceptionType, detail)
                .userMessage(detail)
                .build();

        return this.handleExceptionInternal(ex, entityAlreadyInUseException, new HttpHeaders(), status, request);
    }

    @Override
    protected ResponseEntity<Object> handleTypeMismatch(TypeMismatchException ex, HttpHeaders headers, HttpStatus status, WebRequest request) {

        if (ex instanceof MethodArgumentTypeMismatchException) {
            return handleMethodArgumentTypeMismatchException((MethodArgumentTypeMismatchException) ex, headers, status, request);
        }

        return super.handleTypeMismatch(ex, headers, status, request);
    }

    @Override
    protected ResponseEntity<Object> handleHttpMessageNotReadable(HttpMessageNotReadableException ex, HttpHeaders headers, HttpStatus status, WebRequest request) {

        Throwable rootCause = ExceptionUtils.getRootCause(ex);

        if (rootCause instanceof InvalidFormatException) {
            return handleInvalidFormatException((InvalidFormatException) rootCause, headers, status, request);
        } else if (rootCause instanceof PropertyBindingException) {
            return handlePropertyBindingException((PropertyBindingException) rootCause, headers, status, request);
        }
        ApiExceptionType apiExceptionType = ApiExceptionType.INCOMPREHENSIBLE_MESSAGE;
        String detail = "The request body is invalid. Verify syntax error.";

        ApiException httpMessageNotReadableException = createApiExceptionBuilder(status, apiExceptionType, detail)
                .userMessage(FINAL_USER_GENERIC_ERROR_MESSAGE)
                .build();

        return super.handleExceptionInternal(ex, httpMessageNotReadableException, new HttpHeaders(), status, request);
    }

    @ExceptionHandler(ConstraintValidationException.class)
    public ResponseEntity<Object> handleValidationException(ConstraintValidationException ex, WebRequest request) {
        return this.handleValidationInternal(ex, ex.getBindingResult(), new HttpHeaders(), HttpStatus.BAD_REQUEST, request);
    }

    @Override
    protected ResponseEntity<Object> handleNoHandlerFoundException(NoHandlerFoundException ex, HttpHeaders headers, HttpStatus status, WebRequest request) {
        ApiExceptionType apiExceptionType = ApiExceptionType.RESOURCE_NOT_FOUND;
        String detail = String.format("The resource '%s', which you tried to access, does not exist", ex.getRequestURL());
        ApiException noHandlerFoundException = createApiExceptionBuilder(status, apiExceptionType, detail)
                .userMessage(FINAL_USER_GENERIC_ERROR_MESSAGE)
                .build();

        return this.handleExceptionInternal(ex, noHandlerFoundException, headers, status, request);
    }

    @Override
    protected ResponseEntity<Object> handleMethodArgumentNotValid(MethodArgumentNotValidException ex, HttpHeaders headers, HttpStatus status, WebRequest request) {
        return this.handleValidationInternal(ex, ex.getBindingResult(), headers, status, request);
    }

    private ResponseEntity<Object> handleValidationInternal(Exception ex, BindingResult bindingResult, HttpHeaders headers, HttpStatus status, WebRequest request) {
        ApiExceptionType apiExceptionType = ApiExceptionType.INVALID_DATA;
        String detail = "One or more fields are invalid. Fill in correctly and try again.";

        List<ApiException.Object> apiExceptionObjects = bindingResult.getAllErrors().stream()
                .map(objectError -> {
                    String message = messageSource.getMessage(objectError, LocaleContextHolder.getLocale());

                    String name = objectError.getObjectName();

                    if (objectError instanceof FieldError) {
                        name = ((FieldError) objectError).getField();
                    }

                    return ApiException.Object.builder()
                        .name(name)
                        .userMessage(message)
                        .build();
                })
                .toList();

        ApiException methodArgumentNotValidException = createApiExceptionBuilder(status, apiExceptionType, detail)
                .userMessage(detail)
                .objects(apiExceptionObjects)
                .build();

        return this.handleExceptionInternal(ex, methodArgumentNotValidException, headers, status, request);
    }

    private ResponseEntity<Object> handlePropertyBindingException(PropertyBindingException ex, HttpHeaders headers, HttpStatus status, WebRequest request) {
        ApiExceptionType apiExceptionType = ApiExceptionType.INCOMPREHENSIBLE_MESSAGE;
        String propertyName = this.getExceptionRootCausePath(ex.getPath());
        String detail = String.format("Unknown property '%s' sent in the request. Please, fix or remove this property and try again", propertyName);

        ApiException propertyBindingException = createApiExceptionBuilder(status, apiExceptionType, detail)
                .userMessage(FINAL_USER_GENERIC_ERROR_MESSAGE)
                .build();

        return this.handleExceptionInternal(ex, propertyBindingException, headers, status, request);
    }

    private ResponseEntity<Object> handleInvalidFormatException(InvalidFormatException ex, HttpHeaders headers, HttpStatus status, WebRequest request) {
        ApiExceptionType apiExceptionType = ApiExceptionType.INCOMPREHENSIBLE_MESSAGE;
        String path = this.getExceptionRootCausePath(ex.getPath());

        String detail = String.format("The property '%s' received value '%s', "
        + "which is from an invalid type. Fix it to send a value compatible with type %s.", path, ex.getValue(), ex.getTargetType().getSimpleName());

        ApiException invalidFormatException = createApiExceptionBuilder(status, apiExceptionType, detail)
                .userMessage(FINAL_USER_GENERIC_ERROR_MESSAGE)
                .build();

        return this.handleExceptionInternal(ex, invalidFormatException, headers, status ,request);
    }

    private ResponseEntity<Object> handleMethodArgumentTypeMismatchException(MethodArgumentTypeMismatchException ex, HttpHeaders headers, HttpStatus status, WebRequest request) {
        ApiExceptionType apiExceptionType = ApiExceptionType.INVALID_PARAMETER;
        String detail = String.format("The URL parameter '%s' was given the value '%s', "
                        + "which is of an invalid type. Correct and enter a value compatible with type %s.", ex.getName(), ex.getValue(), Objects.requireNonNull(ex.getRequiredType()).getSimpleName());
        ApiException typeMismatchException = createApiExceptionBuilder(status, apiExceptionType, detail)
                .userMessage(FINAL_USER_GENERIC_ERROR_MESSAGE)
                .build();
        return this.handleExceptionInternal(ex, typeMismatchException, headers, status, request);
    }

    @Override
    protected ResponseEntity<Object> handleExceptionInternal(Exception ex, Object body, HttpHeaders headers, HttpStatus status, WebRequest request) {

        if (Objects.isNull(body)) {
            body = ApiException.builder()
                    .timestamp(OffsetDateTime.now())
                    .title(status.getReasonPhrase())
                    .status(status.value())
                    .userMessage(FINAL_USER_GENERIC_ERROR_MESSAGE)
                    .build();
        } else if (body instanceof String) {
            body = ApiException.builder()
                    .timestamp(OffsetDateTime.now())
                    .title((String) body)
                    .status(status.value())
                    .userMessage(FINAL_USER_GENERIC_ERROR_MESSAGE)
                    .build();
        }

        return super.handleExceptionInternal(ex, body, headers, status, request);
    }

    private ApiException.ApiExceptionBuilder createApiExceptionBuilder(HttpStatus status,
            ApiExceptionType apiExceptionType, String detail) {
        return ApiException.builder()
                .timestamp(OffsetDateTime.now())
                .status(status.value())
                .type(apiExceptionType.getUri())
                .title(apiExceptionType.getTitle())
                .detail(detail);
    }

    private String getExceptionRootCausePath(List<JsonMappingException.Reference> references) {
        return references.stream()
                .map(JsonMappingException.Reference::getFieldName)
                .collect(Collectors.joining("."));
    }
}
