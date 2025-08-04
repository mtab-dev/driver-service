package dev.system.auth_service.application.interfaces;

import dev.system.auth_service.domain.entities.UserEntity;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Map;
import java.util.UUID;

public interface IUserRepository {
    UserDetails findByUsername(String username);
    Map<String, Object> save(UserEntity user);
    void deleteById(UUID id, String username);
}
