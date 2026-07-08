package com.monitoramento.api_monitoramento.services;

import com.monitoramento.api_monitoramento.entity.users.LoginRequest;
import com.monitoramento.api_monitoramento.entity.users.User;
import com.monitoramento.api_monitoramento.exceptions.TokenException;
import com.monitoramento.api_monitoramento.exceptions.UserNotAuthenticated;
import com.monitoramento.api_monitoramento.exceptions.UserWrongCredentials;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.oauth2.jwt.*;
import org.springframework.stereotype.Service;

import java.time.Instant;
import java.util.*;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class TokenService {

    @Autowired
    private final AuthenticationManager authenticationManager;
    @Autowired
    private final JwtEncoder jwtEncoder;

    @Autowired
    private final JwtDecoder jwtDecoder;

    public Map<String,String> refreshToken(User user, String refreshToken) {
        try {
            // 1. Decodifica e valida o Refresh Token usando a chave pública
            Jwt decodedJwt = jwtDecoder.decode(refreshToken);

            // 2. Verifica se realmente é um Refresh Token (evita que usem um Access Token aqui)
            if (!"REFRESH".equals(decodedJwt.getClaimAsString("type"))) {
                throw new TokenException("Token inválido para renovação");
            }

            // 3. Pega o usuário que estava dentro do token
            String username = decodedJwt.getSubject();
            Instant now = Instant.now();

            // 4. Gera um NOVO Access Token (mais 1 hora)
            String newAccessToken = this.createJwtToken(3600,username,"ACESS",user.getAuthorities());

            Map<String, String> response = new HashMap<>();
            response.put("accessToken", newAccessToken);

            // Opcional: Você pode devolver o mesmo refresh token, ou gerar um novo aqui também.
            // Geralmente, mantemos o mesmo para garantir que a sessão total morra em 8h cravadas.
            response.put("refreshToken", refreshToken);

            return response;
        }
        catch(JwtException e){
            throw new UserNotAuthenticated("Sessao expirada, faça login novamente");
        }
    }


    public Map<String,String> generateToken(LoginRequest loginRequest){
        UsernamePasswordAuthenticationToken auth = new UsernamePasswordAuthenticationToken(loginRequest.username(),loginRequest.password());

        Authentication authentication;// = authenticationManager.authenticate(auth);

        try {
            authentication = authenticationManager.authenticate(auth);
        } catch (BadCredentialsException e) { // <-- Alterado para Exception genérica para pegar TUDO
            throw new UserWrongCredentials("is wrong credentials typed");
        } catch (AuthenticationException e) {
            throw new UserNotAuthenticated("User: " + loginRequest.username() + " not authenticating in the server");
        }


        Map<String, String> response = new HashMap<>();

        response.put("acessToken",createJwtToken(3600,authentication.getName(),"ACESS",authentication.getAuthorities()));
        response.put("refreshToken",createJwtToken(28000, authentication.getName(),"REFRESH",authentication.getAuthorities()));

        return response;
    }

    private String createJwtToken(Integer seconds, String username, String claim, Collection<? extends GrantedAuthority> role){
        Instant now = this.now();

        JwsHeader jwsHeader = JwsHeader.with(() -> "RS256").build();

        List<String> rolesUser = role.stream().map(GrantedAuthority::getAuthority).toList();

        JwtClaimsSet jwtClaimsSet = JwtClaimsSet.builder()
                .issuer("api-monitoramento")
                .issuedAt(now)
                .expiresAt(now.plusSeconds(seconds))
                .subject(username)
                .claim("type",claim)
                .claim("role",rolesUser)
                .build();

        JwtEncoderParameters jwtEncoderParameters = JwtEncoderParameters.from(jwsHeader,jwtClaimsSet);


        return jwtEncoder.encode(jwtEncoderParameters).getTokenValue();

    }


    private Instant now(){
        return Instant.now();
    }

    private Instant timeExpire(Instant time){
        return time.plusSeconds(3600);
    }
}
