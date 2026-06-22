package com.monitoramento.api_monitoramento;

import com.monitoramento.api_monitoramento.interfaces.StorageService;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.security.autoconfigure.SecurityAutoConfiguration;
import org.springframework.context.annotation.Bean;

@SpringBootApplication()
public class ApiMonitoramentoApplication {

	// initializate default path
	@Bean
	CommandLineRunner init(StorageService storageService){
		return (args -> {
			storageService.init();
		});
	}

	public static void main(String[] args) {
		SpringApplication.run(ApiMonitoramentoApplication.class, args);
	}


}
