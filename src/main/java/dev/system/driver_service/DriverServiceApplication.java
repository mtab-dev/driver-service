package dev.system.driver_service;
import io.github.cdimascio.dotenv.Dotenv;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class DriverServiceApplication {

	public static void main(String[] args) {
		Dotenv dotenv = Dotenv.configure().load();

		String port = System.getenv().getOrDefault("PORT", "5240");
		System.setProperty("server.port", port);

		dotenv.entries().forEach(entry -> System.setProperty(entry.getKey(), entry.getValue()));

		SpringApplication.run(DriverServiceApplication.class, args);
	}

}
