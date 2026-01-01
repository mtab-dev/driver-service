package dev.system.driver_service.application.usecases;

import dev.system.driver_service.application.interfaces.ISearchUseCase;
import dev.system.driver_service.application.interfaces.IUserRepository;
import dev.system.driver_service.domain.entities.UserEntity;
import dev.system.driver_service.domain.enums.RoleEnum;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.Map;

@Service
public class SearchUseCase implements ISearchUseCase {

    private final IUserRepository repository;

    public SearchUseCase(IUserRepository repository) {
        this.repository = repository;
    }

    @Override
    public Map<String, Object> run(String search, String role, int  page, int size) {
        Pageable pageable = PageRequest.of(page-1, size);
        Page<UserEntity> result = null;

        if (search != null && !search.isBlank() && role != null && !role.isBlank()) {
            result = repository.findBySearchAndRole(search, RoleEnum.valueOf(role), pageable);
        }
        if (search != null && !search.isBlank()) {
            result = repository.findBySearch(search, pageable);
        }
        if (role != null && !role.isBlank()) {
            result = repository.findByRole(RoleEnum.valueOf(role), pageable);
        }

        if(role == null && search == null) result = repository.findAll(pageable);

        return Map.of(
                "result", result.getContent(),
                "totalPages", result.getTotalPages(),
                "totalElements", result.getTotalElements(),
                "currentPage", result.getNumber()
        );
    }
}
