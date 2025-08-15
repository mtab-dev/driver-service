package dev.system.auth_service.infra.repositories;

import dev.system.auth_service.domain.entities.UserEntity;
import dev.system.auth_service.domain.enums.RoleEnum;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Repository
public interface JpaUserRepository extends JpaRepository<UserEntity, UUID> {
    UserDetails findByUsername(String username);
    Optional<UserEntity> findByEmail(String email);
    @Query("SELECT u FROM users u WHERE " +
            "LOWER(u.name) LIKE LOWER(CONCAT('%', :search, '%')) OR " +
            "LOWER(u.username) LIKE LOWER(CONCAT('%', :search, '%')) OR " +
            "LOWER(u.email) LIKE LOWER(CONCAT('%', :search, '%'))")
    Page<UserEntity> findBySearch(String search, Pageable pageable);
    @Query("SELECT u FROM users u WHERE " +
            "(LOWER(u.name) LIKE LOWER(CONCAT('%', :search, '%')) OR " +
            "LOWER(u.username) LIKE LOWER(CONCAT('%', :search, '%')) OR " +
            "LOWER(u.email) LIKE LOWER(CONCAT('%', :search, '%'))) AND u.role = :role")
    Page<UserEntity> findBySearchAndRole(String search, RoleEnum role, Pageable pageable);
    Page<UserEntity> findByRole(RoleEnum role, Pageable pageable);
    Page<UserEntity> findAll(Pageable pageable);
    void deleteById(UUID id);
    boolean existsById(UUID id);


}
