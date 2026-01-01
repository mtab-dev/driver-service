package dev.system.driver_service.application.interfaces;

import dev.system.driver_service.domain.entities.UserEntity;
import dev.system.driver_service.domain.enums.RoleEnum;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.Map;
import java.util.Optional;
import java.util.UUID;

public interface IUserRepository {
    UserDetails findByUsername(String username);
    Map<String, Object> save(UserEntity user);
    Optional<UserEntity> findByEmail(String email);
    Page<UserEntity> findBySearch(String search, Pageable pageable);
    Page<UserEntity> findBySearchAndRole(String search, RoleEnum role, Pageable pageable);
    Page<UserEntity> findByRole(RoleEnum role, Pageable pageable);
    Page<UserEntity> findAll(Pageable pageable);
    Map<String, Object> deleteById(UUID id);
    Optional<UserEntity> findById(UUID id);
}
