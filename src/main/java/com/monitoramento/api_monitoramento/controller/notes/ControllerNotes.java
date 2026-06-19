package com.monitoramento.api_monitoramento.controller.notes;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController("/notes")
public class ControllerNotes {

    @GetMapping("/create")
    public ResponseEntity<?> createNotes(){
        return ResponseEntity.ok().build();
    }


}
