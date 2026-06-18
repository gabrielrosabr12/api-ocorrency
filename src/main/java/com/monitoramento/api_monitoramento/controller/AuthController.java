package com.monitoramento.api_monitoramento.controller;

import com.monitoramento.api_monitoramento.entity.users.User;
import com.monitoramento.api_monitoramento.entity.users.UserDto;
import com.monitoramento.api_monitoramento.services.UsersServices;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/auth")
@AllArgsConstructor
public class AuthController {

    @Autowired public final UsersServices usersServices;

    @PostMapping("/register")
    public ResponseEntity<?> register(@RequestBody UserDto userDto){
        if(usersServices.createUser(userDto)){
            return ResponseEntity.ok().body("Usuario criado com sucesso!!");
        }
        return ResponseEntity.badRequest().build();
    }
}
