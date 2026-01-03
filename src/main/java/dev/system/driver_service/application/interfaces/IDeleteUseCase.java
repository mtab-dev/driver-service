package dev.system.driver_service.application.interfaces;

import dev.system.driver_service.domain.dto.response.StandardResponseDTO;
import org.springframework.http.ResponseEntity;

import java.util.Map;
import java.util.UUID;

public interface IDeleteUseCase {
    ResponseEntity<StandardResponseDTO> run ();
}
