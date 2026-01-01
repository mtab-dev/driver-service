package dev.system.driver_service.application.interfaces;

import dev.system.driver_service.domain.dto.request.UpdateValidationDTO;
import org.springframework.http.ResponseEntity;

import java.util.Map;

public interface IUpdateValidationUseCase {
    ResponseEntity<Map<String, Object>> run(UpdateValidationDTO dto);
}
