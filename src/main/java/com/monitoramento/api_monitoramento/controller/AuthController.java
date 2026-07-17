package com.monitoramento.api_monitoramento.controller;

import com.monitoramento.api_monitoramento.entity.users.LoginRequest;
import com.monitoramento.api_monitoramento.entity.users.RefreshTokenDto;
import com.monitoramento.api_monitoramento.entity.users.User;
import com.monitoramento.api_monitoramento.entity.users.UserDto;
import com.monitoramento.api_monitoramento.services.TokenService;
import com.monitoramento.api_monitoramento.services.UsersServices;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.jackson.autoconfigure.JacksonProperties;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.oauth2.jwt.*;
import org.springframework.web.bind.annotation.*;
import tools.jackson.core.json.JsonFactory;

import java.time.Instant;
import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/auth")
@AllArgsConstructor
@CrossOrigin(origins = "http://localhost:4200")
public class AuthController {

    @Autowired private final UsersServices usersServices;

    @Autowired private final TokenService tokenService;


    @PostMapping("/login")
    public ResponseEntity<Map<String,String>> login (@RequestBody LoginRequest loginRequest){

        Map<String,String> token = this.tokenService.generateToken(loginRequest);

        return ResponseEntity.ok(token);

    }

    @PostMapping("/refreshtoken")
    public ResponseEntity<Map<String,String>> refreshToken(@RequestBody RefreshTokenDto refreshToken){
        System.out.println("CHEGOU AQUI");
        return ResponseEntity.ok(tokenService.refreshToken(refreshToken.refreshToken()));
    }


    @PostMapping("/register")
    public ResponseEntity<?> register(@RequestBody UserDto userDto){
        if(usersServices.createUser(userDto)){
            return ResponseEntity.ok(userDto);
        }
        return ResponseEntity.badRequest().build();
    }
}
