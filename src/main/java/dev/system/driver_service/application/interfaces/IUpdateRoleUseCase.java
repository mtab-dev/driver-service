package dev.system.driver_service.application.interfaces;

import org.springframework.http.ResponseEntity;

import java.util.Map;

public interface IUpdateRoleUseCase {
    ResponseEntity<Map<String,Object>> run(UpdateRoleDTO dto);
}
