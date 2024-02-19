package org.liverpool.smtp.ticket_electronico.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.context.request.WebRequest;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;

import java.util.HashMap;
import java.util.Map;
@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(EnvioEmailException.class)
    public ResponseEntity<Object> handleEnvioEmailException(EnvioEmailException ex) {
        Map<String, Object> response = new HashMap<>();
        response.put("status", "nok");
        response.put("error", ex.getMessage());
        return ResponseEntity.badRequest().body(response);
    }

    @ExceptionHandler(HardBounceException.class)
    public ResponseEntity<Object> handleHardBounceException(HardBounceException ex) {
        Map<String, String> response = new HashMap<>();
        response.put("status", ex.getMessage());
        return ResponseEntity.badRequest().body(response);
    }
}
