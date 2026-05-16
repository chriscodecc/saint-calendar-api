package com.dev.saintcalendar.exeption;

import com.dev.saintcalendar.config.DataSeeder;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import jakarta.persistence.EntityNotFoundException;

@RestControllerAdvice
public class ExceptionHandlingController {

    private final DataSeeder dataSeeder;

    ExceptionHandlingController(DataSeeder dataSeeder) {
        this.dataSeeder = dataSeeder;
    }

    @ResponseStatus(value = HttpStatus.BAD_REQUEST)
    @ExceptionHandler(IllegalArgumentException.class) 
    public ResponseEntity<Map<String, String>> monthOutOfRangeConflict(RuntimeException ex){
        String errorsMString = ex.getMessage();
        HashMap<String, String> errorMap = new HashMap<>();

        errorMap.put("error", errorsMString);  
        return ResponseEntity.badRequest().body(errorMap);
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<Map<String, String>> handleValidationExeptions(MethodArgumentNotValidException ex) {

        List<FieldError> allErrors = ex.getBindingResult().getFieldErrors();
        HashMap<String, String> errorMap = new HashMap<>();

        for (var error : allErrors){
            String badField = error.getField();
            String message = error.getDefaultMessage();
            errorMap.put(badField, message);
        }
        return ResponseEntity.badRequest().body(errorMap);
    }
    
    @ExceptionHandler(EntityNotFoundException.class)
    public ResponseEntity<Map<String,String>> saintNotFoundException(EntityNotFoundException ex){
        String errorMString = ex.getMessage();
        HashMap<String,String> errorMap = new HashMap<>();

        errorMap.put("error", errorMString);

        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(errorMap);
    }
}