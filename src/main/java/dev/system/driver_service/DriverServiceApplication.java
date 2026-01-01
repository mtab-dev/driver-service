package dev.system.driver_service;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class DriverServiceApplication {

	public static void main(String[] args) {
		String port = System.getenv().getOrDefault("PORT", "5240");
		System.setProperty("server.port", port);
		SpringApplication.run(DriverServiceApplication.class, args);
	}

}
