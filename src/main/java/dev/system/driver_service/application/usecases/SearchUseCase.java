package dev.system.driver_service.application.usecases;

import dev.system.driver_service.application.interfaces.ISearchUseCase;
import dev.system.driver_service.application.interfaces.IUserRepository;
import dev.system.driver_service.domain.entities.DriverEntity;
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
        Page<DriverEntity> result = null;

        if(role == null && search == null) result = repository.findAll(pageable);

        return Map.of(
                "result", result.getContent(),
                "totalPages", result.getTotalPages(),
                "totalElements", result.getTotalElements(),
                "currentPage", result.getNumber()
        );
    }
}
