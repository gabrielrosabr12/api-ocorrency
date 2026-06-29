package com.monitoramento.api_monitoramento.controller;

import com.monitoramento.api_monitoramento.entity.users.LoginRequest;
import com.monitoramento.api_monitoramento.entity.users.User;
import com.monitoramento.api_monitoramento.entity.users.UserDto;
import com.monitoramento.api_monitoramento.services.UsersServices;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.oauth2.jwt.JwsHeader;
import org.springframework.security.oauth2.jwt.JwtClaimsSet;
import org.springframework.security.oauth2.jwt.JwtEncoder;
import org.springframework.security.oauth2.jwt.JwtEncoderParameters;
import org.springframework.web.bind.annotation.*;

import java.time.Instant;

@RestController
@RequestMapping("/auth")
@AllArgsConstructor
public class AuthController {

    @Autowired public final UsersServices usersServices;

    @Autowired
    private final AuthenticationManager authenticationManager;
    @Autowired
    private final JwtEncoder jwtEncoder;

    @PostMapping("/login")
    public ResponseEntity<?> login (@RequestBody LoginRequest loginRequest){
        UsernamePasswordAuthenticationToken auth = new UsernamePasswordAuthenticationToken(loginRequest.username(),loginRequest.password());

        var authentication = authenticationManager.authenticate(auth);

        if (!authentication.isAuthenticated()){
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();
        }

        Instant agora = Instant.now();

        JwtClaimsSet claimsSet = JwtClaimsSet.builder()
                .issuer("api-monitoramento")
                .issuedAt(agora)
                .expiresAt(agora.plusSeconds(3600))
                .subject(authentication.getName())
                .build();

        JwsHeader jwsHeader = JwsHeader.with(() -> "RS256").build();

        JwtEncoderParameters jwtEncoderParameters = JwtEncoderParameters.from(jwsHeader,claimsSet);

        return ResponseEntity.ok(jwtEncoder.encode(jwtEncoderParameters).getTokenValue());

    }

    @PostMapping("/register")
    public ResponseEntity<?> register(@RequestBody UserDto userDto){
        if(usersServices.createUser(userDto)){
            return ResponseEntity.ok().body("Usuario criado com sucesso!!");
        }
        return ResponseEntity.badRequest().build();
    }
}
