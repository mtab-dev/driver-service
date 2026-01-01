package dev.system.driver_service.application.usecases;

import dev.system.driver_service.application.interfaces.IUpdateValidationUseCase;
import dev.system.driver_service.application.interfaces.IUserRepository;
import dev.system.driver_service.domain.dto.request.UpdateValidationDTO;
import dev.system.driver_service.domain.enums.ValidationEnum;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.Map;

@Service
public class UpdateValidationUseCase implements IUpdateValidationUseCase {

    private final IUserRepository repository;

    public UpdateValidationUseCase(IUserRepository repository) {
        this.repository = repository;
    }

    @Override
    public ResponseEntity<Map<String, Object>> run(UpdateValidationDTO dto) {
        var userToUpdate = repository.findById(dto.id());

        if (userToUpdate.isEmpty()) return ResponseEntity.notFound().build();

        var user = userToUpdate.get();

        user.setValidation(ValidationEnum.valueOf(dto.validation()));
        user.setUpdatedAt(LocalDateTime.now());

        var response = repository.save(user);
        return ResponseEntity.ok(response);
    }
}
