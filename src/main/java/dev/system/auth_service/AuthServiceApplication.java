package dev.system.auth_service;

import io.github.cdimascio.dotenv.Dotenv;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class AuthServiceApplication {

	public static void main(String[] args) {
		Dotenv dotenv  = Dotenv.configure().load();

		String port = dotenv.get("PORT", "5240");
		System.setProperty("server.port", port);

		dotenv.entries().forEach(entry -> System.setProperty(entry.getKey(), entry.getValue()));
		SpringApplication.run(AuthServiceApplication.class, args);
	}

}
