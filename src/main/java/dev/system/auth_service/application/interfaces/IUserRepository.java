package dev.system.auth_service.application.interfaces;

import dev.system.auth_service.domain.entities.UserEntity;

import java.util.Optional;

public interface IUserRepository {
    Optional<UserEntity> findByUsername(String username);
    UserEntity save(UserEntity user);

}
