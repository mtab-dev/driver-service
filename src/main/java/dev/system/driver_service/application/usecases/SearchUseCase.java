package dev.system.driver_service.application.usecases;

import dev.system.driver_service.application.interfaces.ISearchUseCase;
import dev.system.driver_service.application.interfaces.IUserRepository;
import dev.system.driver_service.domain.entities.DriverEntity;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.sql.Driver;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.UUID;

@Service
public class SearchUseCase implements ISearchUseCase {

    private final IUserRepository repository;

    public SearchUseCase(IUserRepository repository) {
        this.repository = repository;
    }

    @Override
    public Map<String, Object> run(String search, String role, int  page, int size) {
        Pageable pageable = PageRequest.of(page-1, size);
        Page<DriverEntity> result;

        if (isUUID(search)) {
            UUID id = UUID.fromString(search);
            Optional<DriverEntity> foundId = repository.findById(id);
            return Map.of(
                    "driver", foundId.get()
            );
        }
        if(search == null || search.isBlank()) {
            result = repository.findAll(pageable);
        }
        else{
            result = repository.findBySearch(search, pageable);
        }

        return Map.of(
                "result", result.getContent(),
                "totalPages", result.getTotalPages(),
                "totalElements", result.getTotalElements(),
                "currentPage", result.getNumber()
        );
    }

    private boolean isUUID(String value){
        if(value==null || value.isBlank()) return false;
        try{
            UUID.fromString(value);

            return true;
        }catch (IllegalArgumentException e){
            return false;
        }

    }
}
