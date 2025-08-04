package dev.system.auth_service.application.interfaces;

import dev.system.auth_service.domain.dto.request.RegisterDTO;
import dev.system.auth_service.domain.entities.UserEntity;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.Map;

public interface IRegisterUseCase {
    public ResponseEntity<UserEntity> run (@RequestBody @Valid RegisterDTO dto);
}
