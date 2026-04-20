package com.dev.saintcalendar.exeption;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class ExeptionHandelingController {

    @ResponseStatus(value = HttpStatus.BAD_REQUEST)
    @ExceptionHandler(IllegalArgumentException.class) 
    public String monthOutOfRangeConflict(RuntimeException ex){
        return "this is not even a month";
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
    

}