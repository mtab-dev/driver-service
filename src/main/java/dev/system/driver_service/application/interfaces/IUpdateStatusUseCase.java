package dev.system.driver_service.application.interfaces;

import dev.system.driver_service.domain.dto.request.UpdateStatusDTO;
import org.springframework.http.ResponseEntity;

import java.util.Map;

public interface IUpdateStatusUseCase {
    ResponseEntity<Map<String, Object>> run(UpdateStatusDTO dto);
}
