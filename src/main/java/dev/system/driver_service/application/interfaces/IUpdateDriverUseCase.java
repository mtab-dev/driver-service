package dev.system.driver_service.application.interfaces;

import dev.system.driver_service.domain.dto.request.UpdateDriverDTO;
import dev.system.driver_service.domain.dto.response.StandardResponseDTO;
import org.springframework.http.ResponseEntity;

import java.util.Map;

public interface IUpdateDriverUseCase {
    ResponseEntity<StandardResponseDTO> run(UpdateDriverDTO dto);
}
