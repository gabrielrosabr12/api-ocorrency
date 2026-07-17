package com.monitoramento.api_monitoramento.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.http.ProblemDetail;

public class UserNotAuthenticated extends OcorrencyExceptions {

   private final String detail;

    public UserNotAuthenticated(String detail){
        this.detail = detail;
    }


    @Override
    public ProblemDetail toProblem() {
        var pb = ProblemDetail.forStatus(HttpStatus.FORBIDDEN);

        pb.setTitle("User not authenticated");

        pb.setDetail(this.detail);

        return pb;
    }
}
