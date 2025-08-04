package dev.system.auth_service.application.usecases;

import dev.system.auth_service.application.interfaces.ILoginUseCase;
import dev.system.auth_service.domain.dto.request.LoginDTO;
import dev.system.auth_service.domain.dto.response.LoginResponseDTO;
import dev.system.auth_service.domain.entities.UserEntity;
import dev.system.auth_service.infra.security.TokenService;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;

@Service
public class LoginUseCase implements ILoginUseCase {

    private final AuthenticationManager authenticationManager;
    private final TokenService tokenService;

    public LoginUseCase(AuthenticationManager authenticationManager, TokenService tokenService) {
        this.authenticationManager = authenticationManager;
        this.tokenService = tokenService;
    }

    @Override
    public ResponseEntity<Map<String, Object>> run(LoginDTO dto) {
        var auth = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(dto.username(), dto.password())
        );

        var token = tokenService.generateToken((UserEntity) auth.getPrincipal());

        Map<String, Object> response = new HashMap<>();

        response.put("status", "success");
        response.put("token", token);


        return ResponseEntity.ok(response);
    }
}
