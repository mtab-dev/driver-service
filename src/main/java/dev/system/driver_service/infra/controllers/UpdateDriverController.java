package dev.system.driver_service.infra.controllers;

import dev.system.driver_service.application.interfaces.IUpdateDriverUseCase;
import dev.system.driver_service.domain.dto.request.UpdateDriverDTO;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

@RestController
@RequestMapping("/auth")
public class UpdateDriverController {

    private final IUpdateDriverUseCase usecase;

    public UpdateDriverController(IUpdateDriverUseCase usecase) {
        this.usecase = usecase;
    }

    @PutMapping("/driver/update")
    public ResponseEntity<Map<String,Object>> update(@RequestBody UpdateDriverDTO dto) {
        return this.usecase.run(dto);
    }
}
