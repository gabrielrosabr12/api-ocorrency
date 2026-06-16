package com.monitoramento.api_monitoramento.config;

import com.monitoramento.api_monitoramento.entity.UserType;
import com.monitoramento.api_monitoramento.repository.UserTypeRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Configuration;

import java.util.Arrays;

@Configuration
public class DataLoader implements CommandLineRunner {

    private final UserTypeRepository userTypeRepository;

    public DataLoader(UserTypeRepository userTypeRepository) {
        this.userTypeRepository = userTypeRepository;
    }

    @Override
    public void run(String... args) throws Exception {
        Arrays.stream(UserType.Enum.values())
                .forEach(userType -> {
                    userTypeRepository.findById(userType.get().getId())
                        .ifPresentOrElse(user -> {
                            System.out.println("User "+user+" was found");},
                                () -> {
                            userTypeRepository.save(userType.get());
                                }
                                );
        });
    }
}
