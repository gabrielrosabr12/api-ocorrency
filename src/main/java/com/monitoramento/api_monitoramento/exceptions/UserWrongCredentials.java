package com.monitoramento.api_monitoramento.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.http.ProblemDetail;

public class UserWrongCredentials extends OcorrencyExceptions {

    private final String detail;

    public UserWrongCredentials(String detail){
        this.detail = detail;
    }

    @Override
    public ProblemDetail toProblem() {

        var pb = ProblemDetail.forStatus(HttpStatus.UNAUTHORIZED);

        pb.setTitle("User wrong credentials");

        pb.setDetail(this.detail);

        return pb;
    }
}
