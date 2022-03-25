package net.prolancer.validation.common.controller;

import net.prolancer.validation.common.entity.ResponseHandler;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import java.util.HashMap;
import java.util.Map;

@ControllerAdvice
public class GlobalExceptionHandler extends ResponseEntityExceptionHandler {

    /**
     * Validation Handler - Java Bean Validation Exception
     * @param ex
     * @param headers
     * @param status
     * @param request
     * @return
     */
    @Override
    protected ResponseEntity<Object> handleMethodArgumentNotValid(MethodArgumentNotValidException ex, HttpHeaders headers, HttpStatus status, WebRequest request) {
        Map<String, String> errors = new HashMap<>();

        ex.getBindingResult().getAllErrors().forEach((error) -> {
            String fieldName = ((FieldError) error).getField();
            String message = error.getDefaultMessage();
            errors.put(fieldName, message);
        });

        return ResponseHandler.error("Validation Error", HttpStatus.BAD_REQUEST, errors);
    }

    @ExceptionHandler(value = {IllegalArgumentException.class, IllegalStateException.class})
    protected ResponseEntity<Object> handleConflict(RuntimeException ex, WebRequest request) {
        return ResponseHandler.error("Illegal or inappropriate argument"
                , HttpStatus.CONFLICT
                , getErrorMessage(ex));
    }

    @ExceptionHandler(value = {AccessDeniedException.class})
    public ResponseEntity<Object> handleAccessDeniedException(AccessDeniedException ex, WebRequest request) {
        return ResponseHandler.error("Access denied"
                , HttpStatus.FORBIDDEN
                , getErrorMessage(ex));
    }

    @ExceptionHandler(value = {Exception.class})
    public ResponseEntity<Object> handleExceptions(Exception ex, WebRequest request) {
        return ResponseHandler.error("Unknown Exception"
                , HttpStatus.BAD_REQUEST
                , getErrorMessage(ex));
    }

    /**
     * Return Error Message
     * @param ex
     * @return
     */
    private Map<String, String> getErrorMessage(Exception ex) {
        Map<String, String> errors = new HashMap<>();
        errors.put("exception", ex.getMessage());
        errors.put("cause", ex.getCause().getMessage());
        return errors;
    }
}
