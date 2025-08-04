package dev.system.auth_service.infra.controllers;

import dev.system.auth_service.application.interfaces.IRegisterUseCase;
import dev.system.auth_service.domain.dto.request.RegisterDTO;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

@RestController
@RequestMapping("/auth")
public class RegisterController {

    private final IRegisterUseCase usecase;

    public RegisterController(IRegisterUseCase usecase) {
        this.usecase = usecase;
    }

    @PostMapping("/register")
    public ResponseEntity<Map<String, Object>> perform(@RequestBody @Valid RegisterDTO dto){

        return this.usecase.run(dto);
    }
}
