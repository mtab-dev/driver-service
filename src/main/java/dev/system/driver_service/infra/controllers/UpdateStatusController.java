package dev.system.driver_service.infra.controllers;

import dev.system.driver_service.application.interfaces.IUpdateStatusUseCase;
import dev.system.driver_service.domain.dto.request.UpdateStatusDTO;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

@RestController
@RequestMapping("/auth")
public class UpdateStatusController {

    private final IUpdateStatusUseCase usecase;

    public UpdateStatusController(IUpdateStatusUseCase usecase) {
        this.usecase = usecase;
    }

    @PutMapping("driver/update/status")
    public ResponseEntity<Map<String, Object>> perform(@RequestBody UpdateStatusDTO dto) {
        return this.usecase.run(dto);
    }
}
