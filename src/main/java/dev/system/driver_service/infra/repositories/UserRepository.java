package dev.system.driver_service.infra.repositories;

import dev.system.driver_service.application.interfaces.IUserRepository;
import dev.system.driver_service.domain.entities.DriverEntity;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Repository;

import java.util.*;

@Repository
public class UserRepository implements IUserRepository {

    private final JpaUserRepository repository;

    public UserRepository(JpaUserRepository repository) {
        this.repository = repository;
    }

    @Override
    public Map<String, Object> save(DriverEntity user) {
        var data = repository.save(user);

        Map<String, Object> response = new HashMap<>();
        Map<String, Object> returnUser = new HashMap<>();
        returnUser.put("name", user.getName());
        returnUser.put("email", user.getEmail());
        returnUser.put("id", user.getId());
        returnUser.put("data", data);

        response.put("status", "success");
        response.put("user", returnUser);

        return response;
    }

    @Override
    public Optional<DriverEntity> findByEmail(String email) {
        return repository.findByEmail(email);
    }

    @Override
    public Page<DriverEntity> findBySearch(String search, Pageable pageable) {
        return repository.findBySearch(search, pageable);
    }

    @Override
    public Page<DriverEntity> findAll(Pageable pageable) {
        return repository.findAll(pageable);
    }

    @Override
    public Map<String, Object> deleteById(UUID id) {
        boolean existsBefore = repository.existsById(id);

        repository.deleteById(id);

        Map<String, Object> response = new HashMap<>();
        response.put("deleted", existsBefore);

        return response;
    }

    @Override
    public Optional<DriverEntity> findById(UUID id) {
        return repository.findById(id);
    }

}
