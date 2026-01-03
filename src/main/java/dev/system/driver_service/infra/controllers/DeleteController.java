package dev.system.driver_service.infra.controllers;

import dev.system.driver_service.application.interfaces.IDeleteUseCase;
import dev.system.driver_service.domain.dto.response.StandardResponseDTO;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Map;
import java.util.UUID;

@RestController
@RequestMapping("/auth")
public class DeleteController {

    private final IDeleteUseCase usecase;

    public DeleteController(IDeleteUseCase usecase) {
        this.usecase = usecase;
    }

    @DeleteMapping("/driver/delete/{id}")
    public ResponseEntity<StandardResponseDTO> perform(){
        return this.usecase.run();
    }
}
