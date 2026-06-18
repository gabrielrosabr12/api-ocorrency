package com.monitoramento.api_monitoramento.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.http.ProblemDetail;

public class OcorrencyExceptions extends RuntimeException {

    public ProblemDetail toProblem(){
      var pb = ProblemDetail.forStatus(HttpStatus.INTERNAL_SERVER_ERROR);

      pb.setTitle("Api-Ocorrency Internal Server Error");
      pb.setDetail("No details for errors");
      return pb;
    }
}
