package dev.system.driver_service.application.usecases;

import dev.system.driver_service.application.interfaces.IUpdateStatusUseCase;
import dev.system.driver_service.application.interfaces.IUserRepository;
import dev.system.driver_service.domain.dto.request.UpdateStatusDTO;
import dev.system.driver_service.domain.enums.StatusEnum;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.Map;

@Service
public class UpdateStatusUseCase implements IUpdateStatusUseCase {

    private final IUserRepository repository;

    public UpdateStatusUseCase(IUserRepository repository) {
        this.repository = repository;
    }

    @Override
    public ResponseEntity<Map<String, Object>> run(UpdateStatusDTO dto) {
        var userToUpdate = repository.findById(dto.id());

        if(userToUpdate.isEmpty()) return ResponseEntity.notFound().build();

        var user = userToUpdate.get();

        user.setStatus(StatusEnum.valueOf(dto.status()));
        user.setUpdatedAt(LocalDateTime.now());

        var response = repository.save(user);
        return ResponseEntity.ok(response);
    }
}
