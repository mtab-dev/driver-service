package dev.system.driver_service.application.usecases;

import dev.system.driver_service.application.interfaces.IDeleteUseCase;
import dev.system.driver_service.application.interfaces.IUserRepository;
import dev.system.driver_service.domain.dto.response.StandardResponseDTO;
import dev.system.driver_service.domain.entities.DriverEntity;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.context.SecurityContextHolder;
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
    public ResponseEntity<StandardResponseDTO> run() {
        var auth = SecurityContextHolder.getContext().getAuthentication();

        if(auth == null || !(auth.getPrincipal() instanceof DriverEntity contextUser)) return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();

        var data = repository.deleteById(contextUser.getId());

        if(data == null) return ResponseEntity.status(HttpStatus.NOT_FOUND).build();

        return ResponseEntity.ok(
                new StandardResponseDTO("success", "deleted")
        );
    }
}
