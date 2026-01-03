package dev.system.driver_service.application.usecases;

import dev.system.driver_service.application.interfaces.IUpdateDriverUseCase;
import dev.system.driver_service.application.interfaces.IUserRepository;
import dev.system.driver_service.domain.dto.request.UpdateDriverDTO;
import dev.system.driver_service.domain.dto.response.StandardResponseDTO;
import dev.system.driver_service.domain.entities.DriverEntity;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.Map;
import java.util.Optional;

@Service
public class UpdateDriverUseCase implements IUpdateDriverUseCase {

    private final IUserRepository repository;

    public UpdateDriverUseCase(IUserRepository repository) {
        this.repository = repository;
    }

    @Override
    public ResponseEntity<StandardResponseDTO> run(UpdateDriverDTO dto) {
        var auth = SecurityContextHolder.getContext().getAuthentication();

        if(auth == null || !(auth.getPrincipal() instanceof DriverEntity contextUser)) return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();

        Optional<DriverEntity> userToUpdate = repository.findById(contextUser.getId());
        if(userToUpdate.isEmpty()) return ResponseEntity.notFound().build();

        DriverEntity user = userToUpdate.get();

        if (dto.name() != null) user.setName(dto.name());

        if (dto.email() != null) user.setEmail(dto.email());

        if (dto.password() != null && !dto.password().isEmpty()) {
            String encryptedPassword = new BCryptPasswordEncoder().encode(dto.password());
            user.setPassword(encryptedPassword);
        }

        user.setUpdatedAt(LocalDateTime.now());

        repository.save(user);

        return ResponseEntity.ok(
          new StandardResponseDTO("success", "updated")
        );
    }
}
