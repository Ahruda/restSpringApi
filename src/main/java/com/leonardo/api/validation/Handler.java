package com.leonardo.api.validation;

import org.springframework.http.HttpStatus;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.ArrayList;
import java.util.List;
import java.util.NoSuchElementException;

@RestControllerAdvice
public class Handler {

    @ResponseStatus(code = HttpStatus.BAD_REQUEST)
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public List<?> handleFormDto(MethodArgumentNotValidException exception) {
        List<FieldError> fieldError = exception.getBindingResult().getFieldErrors();
        List<ErrorFormDto> errorsFormDto = new ArrayList<>();
        fieldError.forEach( error -> {
            ErrorFormDto errorFormDto = new ErrorFormDto(error.getDefaultMessage(), error.getField());
            errorsFormDto.add(errorFormDto);
        });
        return errorsFormDto;
    }

    @ResponseStatus(code = HttpStatus.BAD_REQUEST)
    @ExceptionHandler(NoSuchElementException.class)
    public ErrorNotExist handleNotExist(NoSuchElementException exception) {
        return new ErrorNotExist(exception.getMessage());
    }
}
