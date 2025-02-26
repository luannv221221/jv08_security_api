package com.ra.advice;

import com.ra.exception.CustomException;
import com.ra.model.dto.DataError;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.HashMap;
import java.util.Map;

@RestControllerAdvice
public class HandleExceptionController {
    @ExceptionHandler(MethodArgumentNotValidException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public DataError<Map<String,String>> handlerValidException(MethodArgumentNotValidException exception){
        Map<String,String> map = new HashMap<>();
        exception.getFieldErrors().forEach(fieldError ->
                map.put(fieldError.getField(), fieldError.getDefaultMessage())
                );
        return new DataError<>(map,400);
    }
    @ExceptionHandler(CustomException.class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    public DataError<?> handlerValidException(CustomException exception){
        return new DataError<>(exception.getMessage(),404);
    }
}
