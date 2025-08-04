package dev.system.auth_service.application.usecases;

import dev.system.auth_service.application.interfaces.IRegisterUseCase;
import dev.system.auth_service.application.interfaces.IUserRepository;
import dev.system.auth_service.domain.dto.request.RegisterDTO;
import dev.system.auth_service.domain.entities.UserEntity;
import dev.system.auth_service.domain.enums.RoleEnum;
import dev.system.auth_service.domain.helpers.UsernameGenerator;
import jakarta.validation.Valid;
import org.apache.catalina.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.Map;

@Service
public class RegisterUseCase implements IRegisterUseCase {

    private final IUserRepository repository;
    private final UsernameGenerator usernameGenerator;

    public RegisterUseCase(IUserRepository repository, UsernameGenerator usernameGenerator){
        this.repository = repository;
        this.usernameGenerator = usernameGenerator;
    }

    @Override
    public ResponseEntity<UserEntity> run (@Valid RegisterDTO dto) {
        String username = usernameGenerator.generateUsername(dto.name());
        String encryptedPassword = new BCryptPasswordEncoder().encode(dto.password());

        UserEntity newUser = new UserEntity();
        newUser.setUsername(username);
        newUser.setPassword(encryptedPassword);
        newUser.setEmail(dto.email());
        newUser.setName(dto.name());
        newUser.setRole(RoleEnum.CLIENT);
        newUser.setActive(true);

        var response = repository.save(newUser);

        return ResponseEntity.ok(response);
    }
}