package dev.system.auth_service.infra.controllers;

import dev.system.auth_service.application.interfaces.IDeleteUseCase;
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

    @DeleteMapping("/users/delete/{id}")
    public ResponseEntity<Map<String, Object>> perform(@PathVariable UUID id){
        return this.usecase.run(id);
    }
}
