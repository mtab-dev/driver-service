package dev.system.auth_service;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class AuthServiceApplication {

	public static void main(String[] args) {
		String port = System.getenv().getOrDefault("PORT", "5240");
		System.setProperty("server.port", port);
		SpringApplication.run(AuthServiceApplication.class, args);
	}

}
