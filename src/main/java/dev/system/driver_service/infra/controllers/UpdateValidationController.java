package dev.system.driver_service.infra.controllers;

import dev.system.driver_service.application.interfaces.IUpdateValidationUseCase;
import dev.system.driver_service.domain.dto.request.UpdateValidationDTO;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

@RestController
@RequestMapping("/auth")
public class UpdateValidationController {

    private final IUpdateValidationUseCase usecase;

    public UpdateValidationController(IUpdateValidationUseCase usecase) {
        this.usecase = usecase;
    }

    @PutMapping("driver/update/validation")
    public ResponseEntity<Map<String, Object>> perform(@RequestBody UpdateValidationDTO dto){
        return this.usecase.run(dto);
    }

}
