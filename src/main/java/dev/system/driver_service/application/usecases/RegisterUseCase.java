package dev.system.driver_service.application.usecases;

import dev.system.driver_service.application.interfaces.IRegisterUseCase;
import dev.system.driver_service.application.interfaces.IUserRepository;
import dev.system.driver_service.domain.dto.request.RegisterDTO;
import dev.system.driver_service.domain.entities.DriverEntity;
import dev.system.driver_service.domain.enums.StatusEnum;
import dev.system.driver_service.domain.enums.ValidationEnum;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.Map;

@Service
public class RegisterUseCase implements IRegisterUseCase {

    private final IUserRepository repository;

    public RegisterUseCase(IUserRepository repository){
        this.repository = repository;
    }

    @Override
    public ResponseEntity<Map<String, Object>> run (@Valid RegisterDTO dto) {
        String encryptedPassword = new BCryptPasswordEncoder().encode(dto.password());
        String encryptedCPF = new BCryptPasswordEncoder().encode(dto.cpf());
        String encryptedRg = new BCryptPasswordEncoder().encode(dto.rg());
        String encryptedCnh = new BCryptPasswordEncoder().encode(dto.cnh());

        DriverEntity newUser = new DriverEntity();
        newUser.setPassword(encryptedPassword);
        newUser.setEmail(dto.email());
        newUser.setName(dto.name());
        newUser.setStatus(StatusEnum.ACTIVE);
        newUser.setValidation(ValidationEnum.PENDING);
        newUser.setCpf(encryptedCPF);
        newUser.setRg(encryptedRg);
        newUser.setCnh(encryptedCnh);
        newUser.setBirthDate(dto.birthDate());
        newUser.setPhone(dto.phone());
        newUser.setCreatedAt(LocalDateTime.now());
        newUser.setUpdatedAt(LocalDateTime.now());

        var response = repository.save(newUser);

        return ResponseEntity.ok(response);
    }
}