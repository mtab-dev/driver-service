package dev.system.driver_service.application.usecases;

import dev.system.driver_service.application.interfaces.IUpdateUserUseCase;
import dev.system.driver_service.application.interfaces.IUserRepository;
import dev.system.driver_service.domain.dto.request.UpdateUserDTO;
import dev.system.driver_service.domain.entities.UserEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.Map;
import java.util.Optional;

@Service
public class UpdateUserUseCase implements IUpdateUserUseCase {

    private final IUserRepository repository;

    public UpdateUserUseCase(IUserRepository repository) {
        this.repository = repository;
    }

    @Override
    public ResponseEntity<Map<String, Object>> run(UpdateUserDTO dto) {
        Optional<UserEntity> userToUpdate = repository.findById(dto.id());
        if(userToUpdate.isEmpty()) return ResponseEntity.notFound().build();

        UserEntity user = userToUpdate.get();

        if (dto.name() != null) user.setName(dto.name());

        if (dto.email() != null) user.setEmail(dto.email());

        if (dto.password() != null && !dto.password().isEmpty()) {
            String encryptedPassword = new BCryptPasswordEncoder().encode(dto.password());
            user.setPassword(encryptedPassword);
        }

        user.setUpdatedAt(LocalDateTime.now());

        return ResponseEntity.ok(repository.save(user));
    }
}
