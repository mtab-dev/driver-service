package dev.system.auth_service.infra.repositories;

import dev.system.auth_service.application.interfaces.IUserRepository;
import dev.system.auth_service.domain.entities.UserEntity;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Repository;

import java.util.Optional;

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
    public UserEntity save(UserEntity user) {
        var response = repository.save(user);
        return response;
    }

}
