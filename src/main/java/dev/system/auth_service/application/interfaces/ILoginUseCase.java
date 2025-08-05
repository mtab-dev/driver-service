package dev.system.auth_service.application.interfaces;

import dev.system.auth_service.domain.dto.request.LoginDTO;
import org.springframework.http.ResponseEntity;

import java.util.Map;

public interface ILoginUseCase {
    ResponseEntity<Map<String, Object>> run(LoginDTO dto);
}
