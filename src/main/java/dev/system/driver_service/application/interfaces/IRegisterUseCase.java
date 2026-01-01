package dev.system.driver_service.application.interfaces;

import dev.system.driver_service.domain.dto.request.RegisterDTO;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.Map;

public interface IRegisterUseCase {
    public ResponseEntity<Map<String, Object>> run (@RequestBody @Valid RegisterDTO dto);
}
