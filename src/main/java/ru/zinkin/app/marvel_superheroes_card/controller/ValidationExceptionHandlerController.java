package ru.zinkin.app.marvel_superheroes_card.controller;


import jakarta.validation.ConstraintViolationException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import ru.zinkin.app.marvel_superheroes_card.model.response.ValidErrorResponse;
import ru.zinkin.app.marvel_superheroes_card.model.response.Violation;

import java.util.List;

@ControllerAdvice
@Slf4j
public class ValidationExceptionHandlerController {


    @ResponseBody
    @ExceptionHandler(ConstraintViolationException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public ValidErrorResponse validFieldException(ConstraintViolationException e){
        final List<Violation> violationList = e.getConstraintViolations()
                .stream().map(exc -> new Violation(
                        exc.getPropertyPath().toString(),
                        exc.getMessage()
                )).toList();
        return new ValidErrorResponse(violationList);
    }

    @ResponseBody
    @ExceptionHandler(MethodArgumentNotValidException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public ValidErrorResponse validMethodException(MethodArgumentNotValidException e){
        final List<Violation> violationList = e.getBindingResult().getFieldErrors()
                .stream().map(exc -> new Violation(
                        exc.getField(),
                        exc.getDefaultMessage()
                )).toList();
        return new ValidErrorResponse(violationList);
    }

    @ResponseBody
    @ExceptionHandler(RuntimeException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public String validRuntimeException(RuntimeException e){
        log.warn(String.format("Произошло исключение типа : %s \n Сообщение : %s",e.getClass().toString(),e.getMessage()));
        return "Не удалось выполнить действие";
    }
}
