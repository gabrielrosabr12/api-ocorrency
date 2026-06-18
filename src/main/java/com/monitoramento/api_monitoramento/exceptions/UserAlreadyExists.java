package com.monitoramento.api_monitoramento.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.http.ProblemDetail;

public class UserAlreadyExists extends OcorrencyExceptions {

    private final String username;

    public UserAlreadyExists(String username){
        this.username = username;
    }

    @Override
    public ProblemDetail toProblem(){
        var pb = ProblemDetail.forStatus(HttpStatus.UNPROCESSABLE_CONTENT);

        pb.setTitle("User "+this.username+" Already Exists in the Database");
        pb.setDetail("After fetch a user, He was identified already exists ");
        return pb;
    }

}
