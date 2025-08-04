package dev.system.auth_service.application.interfaces;

import dev.system.auth_service.domain.entities.UserEntity;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Optional;

public interface IUserRepository {
    UserDetails findByUsername(String username);
    UserEntity save(UserEntity user);
}
