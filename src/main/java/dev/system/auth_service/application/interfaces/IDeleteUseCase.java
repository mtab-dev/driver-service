package dev.system.auth_service.application.interfaces;

import org.springframework.http.ResponseEntity;

import java.util.Map;
import java.util.UUID;

public interface IDeleteUseCase {
    ResponseEntity<Map<String, Object>> run (UUID id);
}
