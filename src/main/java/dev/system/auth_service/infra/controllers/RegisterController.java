package dev.system.auth_service.infra.controllers;

import dev.system.auth_service.application.interfaces.IRegisterUseCase;
import dev.system.auth_service.application.interfaces.IUserRepository;
import dev.system.auth_service.domain.dto.request.RegisterDTO;
import dev.system.auth_service.domain.entities.UserEntity;
import dev.system.auth_service.domain.enums.RoleEnum;
import dev.system.auth_service.infra.security.TokenService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/auth")
public class RegisterController {

    private final IRegisterUseCase usecase;

    public RegisterController(IRegisterUseCase usecase) {
        this.usecase = usecase;
    }

    @PostMapping("/register")
    public ResponseEntity perform(@RequestBody @Valid RegisterDTO dto){

        return this.usecase.run(dto);
    }
}
