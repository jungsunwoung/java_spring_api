package com.example.jungsunwoung;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

@SpringBootApplication
@EnableJpaAuditing
public class JungsunwoungApplication {

	public static void main(String[] args) {
		SpringApplication.run(JungsunwoungApplication.class, args);
	}

}
