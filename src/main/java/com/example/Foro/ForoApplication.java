package com.example.Foro;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.beans.factory.annotation.Value;

@SpringBootApplication
public class ForoApplication implements CommandLineRunner {

	@Value("${api.security.secret}") // Inyecta el valor desde application.properties
	private String apiSecret;

	public static void main(String[] args) {
		SpringApplication.run(ForoApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {
		System.out.println("El valor de api.security.secret es: " + apiSecret);
	}
}
