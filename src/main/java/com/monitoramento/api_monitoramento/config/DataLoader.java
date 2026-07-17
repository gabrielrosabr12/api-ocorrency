package com.monitoramento.api_monitoramento.config;

import com.monitoramento.api_monitoramento.entity.notes.StatusNote;
import com.monitoramento.api_monitoramento.entity.users.UserType;
import com.monitoramento.api_monitoramento.repository.notes.StatusNoteRepository;
import com.monitoramento.api_monitoramento.repository.users.UserTypeRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Configuration;

import java.util.Arrays;

@Configuration
public class DataLoader implements CommandLineRunner {

    Logger logger = LoggerFactory.getLogger(DataLoader.class);

    private final UserTypeRepository userTypeRepository;
    private final StatusNoteRepository statusNoteRepository;

    public DataLoader(UserTypeRepository userTypeRepository,StatusNoteRepository statusNoteRepository) {
        this.userTypeRepository = userTypeRepository;
        this.statusNoteRepository = statusNoteRepository;
    }

    @Override
    public void run(String... args) throws Exception {
        Arrays.stream(UserType.Enum.values())
                .forEach(userType -> {
                    userTypeRepository.findById(userType.get().getId())
                        .ifPresentOrElse(user -> {
                            logger.info("User "+user+" was found");},
                                () -> {
                            userTypeRepository.save(userType.get());
                                }
                                );
        });

        Arrays.stream(StatusNote.Enum.values()).forEach(statusNote -> {
            statusNoteRepository.findById(statusNote.get().getId())
                    .ifPresentOrElse(status -> {
                        logger.info("Status "+status+" was found");},
                        () -> {
                            statusNoteRepository.save(statusNote.get());
                        }
                    );
                    });
    }
}
