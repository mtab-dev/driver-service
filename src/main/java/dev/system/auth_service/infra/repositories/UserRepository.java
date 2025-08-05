package dev.system.auth_service.infra.repositories;

import dev.system.auth_service.application.interfaces.IUserRepository;
import dev.system.auth_service.domain.entities.UserEntity;
import dev.system.auth_service.domain.enums.RoleEnum;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Repository;

import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

@Repository
public class UserRepository implements IUserRepository {

    private final JpaUserRepository repository;

    public UserRepository(JpaUserRepository repository) {
        this.repository = repository;
    }

    public UserDetails findByUsername(String username){
        return repository.findByUsername(username);
    }


    @Override
    public Map<String, Object> save(UserEntity user) {
        var data = repository.save(user);

        Map<String, Object> response = new HashMap<>();
        Map<String, Object> returnUser = new HashMap<>();
        returnUser.put("name", user.getName());
        returnUser.put("email", user.getEmail());
        returnUser.put("id", user.getId());
        returnUser.put("username", user.getUsername());

        response.put("status", "success");
        response.put("user", returnUser);

        return response;
    }

    @Override
    public Map<String, Object> findByEmail(String email) {
        var data = repository.findByEmail(email);

        Map<String, Object> response = new HashMap<>();

        response.put("status", "success");
        response.put("user", data);

        return response;
    }

    @Override
    public Map<String, Object> findByRole(RoleEnum role) {
        var data = repository.findByRole(role);

        Map<String, Object> response = new HashMap<>();

        response.put("status", "success");
        response.put("user", data);

        return response;
    }

    @Override
    public Map<String, Object> findAll() {
        var data  = repository.findAll();

        Map<String, Object> response = new HashMap<>();

        response.put("status", "success");
        response.put("users", data);

        return response;
    }

    @Override
    public Map<String, Object> deleteById(UUID id) {
        repository.deleteById(id);

        Map<String, Object> response = new HashMap<>();

        response.put("status", "success");

        return response;
    }

}
