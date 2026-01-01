package dev.system.driver_service.infra.controllers;

import dev.system.driver_service.application.interfaces.IUpdateUserUseCase;
import dev.system.driver_service.domain.dto.request.UpdateUserDTO;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

@RestController
@RequestMapping("/auth")
public class UpdateUserController {

    private final IUpdateUserUseCase usecase;

    public UpdateUserController(IUpdateUserUseCase usecase) {
        this.usecase = usecase;
    }

    @PutMapping("/users/update")
    public ResponseEntity<Map<String,Object>> update(@RequestBody UpdateUserDTO dto) {
        return this.usecase.run(dto);
    }
}
