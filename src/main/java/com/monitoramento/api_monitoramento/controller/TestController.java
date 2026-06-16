package com.monitoramento.api_monitoramento.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/")
public class TestController {

    @GetMapping("teste")
    public String teste(){
        return "Está funcionando a autenticacao!!!";
    }

}
