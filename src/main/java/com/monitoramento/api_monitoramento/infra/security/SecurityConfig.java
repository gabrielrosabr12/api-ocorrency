package com.monitoramento.api_monitoramento.infra.security;

import com.monitoramento.api_monitoramento.config.RsaKeys;
import com.nimbusds.jose.JWEAlgorithm;
import com.nimbusds.jose.JWSAlgorithm;
import com.nimbusds.jose.jwk.JWK;
import com.nimbusds.jose.jwk.JWKSet;
import com.nimbusds.jose.jwk.RSAKey;
import com.nimbusds.jose.jwk.source.ImmutableJWKSet;
import com.nimbusds.jose.jwk.source.JWKSource;
import com.nimbusds.jose.proc.SecurityContext;
import com.nimbusds.jwt.JWT;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;

import org.springframework.security.crypto.factory.PasswordEncoderFactories;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.oauth2.jwt.JwtDecoder;
import org.springframework.security.oauth2.jwt.NimbusJwtDecoder;
import org.springframework.security.oauth2.jwt.NimbusJwtEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.CorsConfigurationSource;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;


import java.util.List;


@Configuration
@EnableWebSecurity // (1)
public class SecurityConfig { // (1)

    private final RsaKeys rsaKeys;

    private final JWSAlgorithm jwsAlgorithm = JWSAlgorithm.RS256;

    private final JWEAlgorithm jweAlgorithm = JWEAlgorithm.RSA_OAEP_256;

    public SecurityConfig(RsaKeys rsaKeys) {
        this.rsaKeys = rsaKeys;
    }

    //(1) Apparently a first SecurityFilter autorize the requisition if user has authenticated
    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception{
        http
                .cors(httpSecurityCorsConfigurer -> httpSecurityCorsConfigurer.configurationSource(this.corsConfiguration()))
                .csrf(csrf -> csrf.disable())
                .sessionManagement(session -> session.sessionCreationPolicy(SessionCreationPolicy.STATELESS))
                .authorizeHttpRequests((authorize) -> authorize
                                .requestMatchers(HttpMethod.POST,"/auth/**").permitAll()
                                //.requestMatchers(HttpMethod.POST,"/login").permitAll()
                                .requestMatchers("/api/auth/**").permitAll() // Rota de login pública
                                .requestMatchers("/teste").hasRole("ADMIN")
                                .anyRequest().authenticated()
                        )

                //.oauth2ResourceServer((auth) -> auth.jwt(Customizer.withDefaults()))
                //.httpBasic(Customizer.withDefaults())
//                .formLogin(Customizer.withDefaults())
                //Enable injetion iframes in page html
                .headers((headers) -> headers
                        .frameOptions((frameOptions) -> frameOptions.sameOrigin()));

        return http.build();
    }

    @Bean
    public JwtDecoder jwtDecoder(){
        return NimbusJwtDecoder.withPublicKey(rsaKeys.publicKey()).build();
    }

    @Bean
    public NimbusJwtEncoder jwtEncoder(){
        JWK jwk = new RSAKey.Builder(rsaKeys.publicKey()).privateKey(rsaKeys.privateKey()).build();

        JWKSource<SecurityContext> jwks = new ImmutableJWKSet<>(new JWKSet(jwk));

        return new NimbusJwtEncoder(jwks);
    }

    @Bean
    // Algorithm for decrypt the password
    // Function that is the feature that
    public PasswordEncoder passwordEncoder(){
        return PasswordEncoderFactories.createDelegatingPasswordEncoder();
    }

    @Bean
    public CorsConfigurationSource corsConfiguration(){
        CorsConfiguration configuration = new CorsConfiguration();
        configuration.setAllowedOrigins(List.of("http://localhost:4200"));
        configuration.setAllowedMethods(List.of("GET","POST","DELETE","OPTIONS"));
        configuration.setAllowedHeaders(List.of("*"));

        UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
        source.registerCorsConfiguration("/**",configuration);
        return source;
    }

    @Bean
    public AuthenticationManager authenticationManager(AuthenticationConfiguration authenticationConfiguration) throws Exception {
        return authenticationConfiguration.getAuthenticationManager();
    }

}
