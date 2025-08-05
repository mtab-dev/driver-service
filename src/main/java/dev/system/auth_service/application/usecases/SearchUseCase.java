package dev.system.auth_service.application.usecases;

import dev.system.auth_service.application.interfaces.ISearchUseCase;
import dev.system.auth_service.application.interfaces.IUserRepository;
import dev.system.auth_service.domain.enums.RoleEnum;
import org.springframework.stereotype.Service;

import java.util.Map;

@Service
public class SearchUseCase implements ISearchUseCase {

    private final IUserRepository repository;

    public SearchUseCase(IUserRepository repository) {
        this.repository = repository;
    }

    @Override
    public Map<String, Object> run(String email, String role) {
        if (email != null) {
            return repository.findByEmail(email);
        }
        if (role != null) {
            return repository.findByRole(RoleEnum.valueOf(role));
        }
        return repository.findAll();
    }
}
