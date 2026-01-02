package dev.system.driver_service.application.interfaces;

import dev.system.driver_service.domain.dto.request.UpdateDriverDTO;
import org.springframework.http.ResponseEntity;

import java.util.Map;

public interface IUpdateDriverUseCase {
    ResponseEntity<Map<String,Object>> run(UpdateDriverDTO dto);
}
