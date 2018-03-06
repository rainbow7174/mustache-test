package com.mugen;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

@SpringBootApplication
@EnableJpaAuditing
public class MustacheTestApplication {

	public static void main(String[] args) {
		SpringApplication.run(MustacheTestApplication.class, args);
	}
}
