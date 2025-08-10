package dev.system.auth_service.application.usecases;

import dev.system.auth_service.application.interfaces.ISearchUseCase;
import dev.system.auth_service.application.interfaces.IUserRepository;
import dev.system.auth_service.domain.enums.RoleEnum;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;

@Service
public class SearchUseCase implements ISearchUseCase {

    private final IUserRepository repository;

    public SearchUseCase(IUserRepository repository) {
        this.repository = repository;
    }

    @Override
    public Map<String, Object> run(String email, String role) {
        boolean hasEmail = email != null && !email.isBlank();
        boolean hasRole = role != null && !role.isBlank();

        if (hasEmail && !hasRole) {
            var result = repository.findByEmail(email);
            HashMap<String, Object> response = new HashMap<>();
            response.put("result", result);
            return response;
        }

        if (!hasEmail && hasRole) {
            var result = repository.findByRole(RoleEnum.valueOf(role));
            HashMap<String, Object> response = new HashMap<>();
            response.put("result", result);
            return response;
        }

        if (!hasEmail && !hasRole) {
            return repository.findAll();
        }

        return Map.of(
                "status", "error",
                "message", "Você deve fornecer apenas email ou role, não ambos."
        );
    }
}
