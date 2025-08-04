package dev.system.auth_service.infra.repositories;

import dev.system.auth_service.application.interfaces.IUserRepository;
import dev.system.auth_service.domain.entities.UserEntity;
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
    public void deleteById(UUID id, String username) {

    }

}
