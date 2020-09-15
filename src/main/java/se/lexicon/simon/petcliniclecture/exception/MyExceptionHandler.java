package se.lexicon.simon.petcliniclecture.exception;

import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@RestControllerAdvice
public class MyExceptionHandler extends ResponseEntityExceptionHandler {


    public static final String VALIDATIONS_FAILED = "Validation Failed: One or more validations failed";

    @ExceptionHandler(IllegalArgumentException.class)
    public ResponseEntity<MyExceptionResponse> handleIllegalArgumentException(IllegalArgumentException ex, WebRequest request){

        MyExceptionResponse response = new MyExceptionResponse(
                LocalDateTime.now(),
                HttpStatus.BAD_REQUEST.value(),
                HttpStatus.BAD_REQUEST.name(),
                ex.getMessage(),
                request.getDescription(false)
        );

        return ResponseEntity.badRequest().body(response);
    }

    @ExceptionHandler(ResourceNotFoundException.class)
    public ResponseEntity<MyExceptionResponse> handleResourceNotFoundException(ResourceNotFoundException ex, WebRequest request){

        MyExceptionResponse response = new MyExceptionResponse(
                LocalDateTime.now(),
                HttpStatus.NOT_FOUND.value(),
                HttpStatus.NOT_FOUND.name(),
                ex.getMessage(),
                request.getDescription(false)
        );

        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(response);

    }


    public ValidationErrorResponse buildResponseBody(MethodArgumentNotValidException ex, WebRequest request, HttpStatus status){

        List<Violation> violations = new ArrayList<>();

        for (FieldError fieldError: ex.getBindingResult().getFieldErrors()) {
            violations.add(new Violation(fieldError.getField(),fieldError.getDefaultMessage()));
        }

        for (ObjectError error : ex.getBindingResult().getGlobalErrors()) {
            violations.add(new Violation(error.getObjectName(), error.getDefaultMessage()));
        }

        return new ValidationErrorResponse(
                LocalDateTime.now(),
                status.value(),
                status.name(),
                VALIDATIONS_FAILED,
                request.getDescription(false),
                violations
        );

    }

    @Override
    protected ResponseEntity<Object> handleMethodArgumentNotValid(MethodArgumentNotValidException ex, HttpHeaders headers, HttpStatus status, WebRequest request) {
        return handleExceptionInternal(ex, buildResponseBody(ex, request, status), headers, status, request);
    }


}
