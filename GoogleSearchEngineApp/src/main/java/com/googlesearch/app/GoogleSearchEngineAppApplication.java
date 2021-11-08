package com.googlesearch.app;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;

@SpringBootApplication
@EnableWebSecurity
public class GoogleSearchEngineAppApplication {

	public static void main(String[] args) {
		SpringApplication.run(GoogleSearchEngineAppApplication.class, args);
	}

}
