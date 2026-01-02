package dev.system.driver_service.application.interfaces;

import dev.system.driver_service.domain.entities.DriverEntity;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.Map;
import java.util.Optional;
import java.util.UUID;

public interface IUserRepository {
    Map<String, Object> save(DriverEntity user);
    Optional<DriverEntity> findByEmail(String email);
    Page<DriverEntity> findBySearch(String search, Pageable pageable);
    Page<DriverEntity> findAll(Pageable pageable);
    Map<String, Object> deleteById(UUID id);
    Optional<DriverEntity> findById(UUID id);
}
