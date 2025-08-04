package dev.system.auth_service.application.interfaces;

import dev.system.auth_service.domain.dto.request.LoginDTO;
import org.springframework.http.ResponseEntity;

public interface ILoginUseCase {
    ResponseEntity run(LoginDTO dto);
}
