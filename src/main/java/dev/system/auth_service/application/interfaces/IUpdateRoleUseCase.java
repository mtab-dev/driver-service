package dev.system.auth_service.application.interfaces;

import dev.system.auth_service.domain.dto.request.UpdateRoleDTO;
import org.springframework.http.ResponseEntity;

import java.util.Map;
import java.util.UUID;

public interface IUpdateRoleUseCase {
    ResponseEntity<Map<String,Object>> run(UpdateRoleDTO dto);
}
