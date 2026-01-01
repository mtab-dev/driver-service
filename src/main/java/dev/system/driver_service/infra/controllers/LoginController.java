package dev.system.driver_service.infra.controllers;

import dev.system.driver_service.application.interfaces.ILoginUseCase;
import dev.system.driver_service.domain.dto.request.LoginDTO;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

@RestController
@RequestMapping("/auth")
public class LoginController {

    private final ILoginUseCase usecase;

    public LoginController(ILoginUseCase usecase) {
        this.usecase = usecase;
    }

    @PostMapping("/login")
    public ResponseEntity<Map<String, Object>> perform(@RequestBody @Valid LoginDTO dto){
        return this.usecase.run(dto);
    }
}
