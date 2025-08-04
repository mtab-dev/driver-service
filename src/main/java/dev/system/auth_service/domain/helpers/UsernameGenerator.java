package dev.system.auth_service.domain.helpers;

import dev.system.auth_service.application.interfaces.IUserRepository;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Service;

@Service
public class UsernameGenerator {

    private final IUserRepository userRepository;

    public UsernameGenerator(IUserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public String generateUsername(String fullName) {
        String[] parts = fullName.trim().split("\\s+");
        if (parts.length < 2) {
            throw new IllegalArgumentException("Nome deve conter pelo menos nome e sobrenome");
        }

        String firstNameInitial = parts[0].substring(0, 1).toLowerCase();
        String lastName = parts[parts.length - 1].toLowerCase();

        String baseUsername = firstNameInitial + lastName;

        String username = baseUsername;
        int suffix = 1;


            username = baseUsername + suffix;

        return username;
    }
}
