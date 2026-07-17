package com.monitoramento.api_monitoramento.infra.security;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.userdetails.User;
//import org.springframework.security.core.userdetails.User.UserBuilder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.factory.PasswordEncoderFactories;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

import static org.springframework.security.web.header.writers.frameoptions.XFrameOptionsHeaderWriter.XFrameOptionsMode.SAMEORIGIN;

@Configuration
@EnableWebSecurity // (1)
public class SecurityConfig { // (1)

    //(1) Apparently a first SecurityFilter autorize the requisition if user has authenticated
    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception{
        http
                .csrf(csrf -> csrf.disable())
                .authorizeHttpRequests((authorize) -> authorize
                                .requestMatchers(HttpMethod.POST,"/auth/register").permitAll()
                                .requestMatchers("/teste").hasRole("ADMIN")
                                .anyRequest().authenticated()
                        )
                .httpBasic(Customizer.withDefaults())
                .formLogin(Customizer.withDefaults())
                //Enable injetion iframes in page html
                .headers((headers) -> headers
                        .frameOptions((frameOptions) -> frameOptions.sameOrigin()));

        return http.build();
    }

    @Bean
    // Algorithm for decrypt the password
    // Function that is the feature that
    public PasswordEncoder passwordEncoder(){
        return PasswordEncoderFactories.createDelegatingPasswordEncoder();
    }

}
