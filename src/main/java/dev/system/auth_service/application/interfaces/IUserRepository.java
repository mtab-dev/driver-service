package dev.system.auth_service.application.interfaces;

import dev.system.auth_service.domain.entities.UserEntity;
import dev.system.auth_service.domain.enums.RoleEnum;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.UUID;

public interface IUserRepository {
    UserDetails findByUsername(String username);
    Map<String, Object> save(UserEntity user);
    Optional<UserEntity> findByEmail(String email);
    List<UserEntity> findByRole(RoleEnum role);
    Map<String, Object> findAll();
    Map<String, Object> deleteById(UUID id);
    Optional<UserEntity> findById(UUID id);
}
