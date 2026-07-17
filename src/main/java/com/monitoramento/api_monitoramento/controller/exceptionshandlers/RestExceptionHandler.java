package com.monitoramento.api_monitoramento.controller.exceptionshandlers;

import com.monitoramento.api_monitoramento.exceptions.UserAlreadyExists;
import com.monitoramento.api_monitoramento.exceptions.UserWrongCredentials;
import org.springframework.http.HttpStatus;
import org.springframework.http.ProblemDetail;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class RestExceptionHandler {

    @ExceptionHandler(UserAlreadyExists.class)
    public ProblemDetail userAlreadyExists(UserAlreadyExists us){
        return us.toProblem();
    }

    @ExceptionHandler(UserWrongCredentials.class)
    public ProblemDetail userWrongCredentials(UserWrongCredentials us){
        return us.toProblem();
    }


}
