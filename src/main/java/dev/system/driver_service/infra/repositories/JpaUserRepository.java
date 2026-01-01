package dev.system.driver_service.infra.repositories;

import dev.system.driver_service.domain.entities.DriverEntity;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Repository;

import java.util.Optional;
import java.util.UUID;

@Repository
public interface JpaUserRepository extends JpaRepository<DriverEntity, UUID> {
    UserDetails findByUsername(String username);
    Optional<DriverEntity> findByEmail(String email);
    @Query("SELECT u FROM users u WHERE " +
            "LOWER(u.name) LIKE LOWER(CONCAT('%', :search, '%')) OR " +
            "LOWER(u.username) LIKE LOWER(CONCAT('%', :search, '%')) OR " +
            "LOWER(u.email) LIKE LOWER(CONCAT('%', :search, '%'))")
    Page<DriverEntity> findBySearch(String search, Pageable pageable);
    @Query("SELECT u FROM users u WHERE " +
            "(LOWER(u.name) LIKE LOWER(CONCAT('%', :search, '%')) OR " +
            "LOWER(u.username) LIKE LOWER(CONCAT('%', :search, '%')) OR " +
            "LOWER(u.email) LIKE LOWER(CONCAT('%', :search, '%'))) AND u.role = :role")
    Page<DriverEntity> findBySearchAndRole(String search, RoleEnum role, Pageable pageable);
    Page<DriverEntity> findByRole(RoleEnum role, Pageable pageable);
    Page<DriverEntity> findAll(Pageable pageable);
    void deleteById(UUID id);
    boolean existsById(UUID id);


}
