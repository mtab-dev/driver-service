package dev.system.driver_service.application.usecases;

import dev.system.driver_service.application.interfaces.IDeleteUseCase;
import dev.system.driver_service.application.interfaces.IUserRepository;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.Map;
import java.util.UUID;

@Service
public class DeleteUseCase implements IDeleteUseCase {

    private final IUserRepository repository;

    public DeleteUseCase(IUserRepository repository) {
        this.repository = repository;
    }

    @Override
    public ResponseEntity<Map<String, Object>> run(UUID id) {

        var data = repository.deleteById(id);

        return ResponseEntity.ok(data);
    }
}
