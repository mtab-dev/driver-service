package dev.system.auth_service.application.interfaces;

import dev.system.auth_service.domain.dto.request.UpdateUserDTO;
import org.springframework.http.ResponseEntity;

import java.util.Map;

public interface IUpdateUserUseCase {
    ResponseEntity<Map<String,Object>> run(UpdateUserDTO dto);
}
