package com.monitoramento.api_monitoramento.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.http.ProblemDetail;

public class TokenException extends OcorrencyExceptions {

    private final String detail;

    public TokenException(String detail){
        this.detail = detail;
    }

    @Override
    public ProblemDetail toProblem() {
        var pb = ProblemDetail.forStatus(HttpStatus.INTERNAL_SERVER_ERROR);

        pb.setTitle("Token problem");
        pb.setDetail(this.detail);


        return pb;
    }
}
