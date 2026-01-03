package dev.system.driver_service.application.usecases;

import dev.system.driver_service.application.interfaces.ILoginUseCase;
import dev.system.driver_service.application.interfaces.IUserRepository;
import dev.system.driver_service.domain.dto.request.LoginDTO;
import dev.system.driver_service.domain.entities.DriverEntity;
import dev.system.driver_service.infra.security.TokenService;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;

@Service
public class LoginUseCase implements ILoginUseCase {

    private final TokenService tokenService;
    private final IUserRepository repository;

    public LoginUseCase(TokenService tokenService, IUserRepository repository) {
        this.tokenService = tokenService;
        this.repository = repository;
    }

    @Override
    public ResponseEntity<Map<String, Object>> run(LoginDTO dto) {
        var user = repository.findByEmail(dto.email())
                .orElseThrow(() -> new UsernameNotFoundException("User not found"));
        var encoder = new BCryptPasswordEncoder();

        if(!encoder.matches(dto.password(), user.getPassword())) {
            throw new UsernameNotFoundException("Invalid username or password");
        }


        var token = tokenService.generateToken(user);

        Map<String, Object> response = new HashMap<>();

        response.put("status", "success");
        response.put("token", token);


        return ResponseEntity.ok(response);
    }
}
