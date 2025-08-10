package dev.system.auth_service.infra.repositories;

import dev.system.auth_service.domain.entities.UserEntity;
import dev.system.auth_service.domain.enums.RoleEnum;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Repository
public interface JpaUserRepository extends JpaRepository<UserEntity, UUID> {
    UserDetails findByUsername(String username);
    Optional<UserEntity> findByEmail(String email);
    List<UserEntity> findByRole(RoleEnum role);
    List<UserEntity> findAll();
    Optional<UserEntity> findById(UUID id);
    void deleteById(UUID id);
    boolean existsById(UUID id);


}
