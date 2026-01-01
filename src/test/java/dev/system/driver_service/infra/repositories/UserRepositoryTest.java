package dev.system.driver_service.infra.repositories;

import dev.system.driver_service.application.interfaces.IUserRepository;
import dev.system.driver_service.domain.dto.request.RegisterDTO;
import dev.system.driver_service.domain.entities.DriverEntity;
import jakarta.persistence.EntityManager;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.context.annotation.Import;
import org.springframework.data.domain.Pageable;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.test.context.ActiveProfiles;
import java.util.Map;
import java.util.Optional;
import java.util.UUID;

import static org.assertj.core.api.Assertions.assertThat;



@DataJpaTest
@ActiveProfiles("test")
@Import(UserRepository.class)
class UserRepositoryTest {

    @Autowired
    private IUserRepository repository;

    @Autowired
    EntityManager entityManager;

    private DriverEntity createUser(RegisterDTO dto){
        String username = dto.name().trim();
        String encryptedPassword = new BCryptPasswordEncoder().encode(dto.password());

        DriverEntity newUser = new DriverEntity();
        newUser.setUsername(username);
        newUser.setPassword(encryptedPassword);
        newUser.setEmail(dto.email());
        newUser.setName(dto.name());
        newUser.setRole(RoleEnum.CLIENT);
        newUser.setActive(true);

        entityManager.persist(newUser);
        entityManager.flush(); // garante que o persist seja executado

        return newUser;
    }

    @Test
    @DisplayName("Should get user by username")
    void findByUsernameSuccess() {
        RegisterDTO dto = new RegisterDTO("testpassword","test@email.com","test user");
        DriverEntity user = createUser(dto);

        UserDetails result = repository.findByUsername(user.getUsername());
        assertThat(result.getUsername()).isEqualTo(user.getUsername());
    }

    @Test
    @DisplayName("Should not get user by username")
    void findByUsernameFailure() {
        UserDetails result = repository.findByUsername("nonexistent");
        assertThat(result).isNull();
    }

    @Test
    @DisplayName("Should get user by email")
    void findByEmailSuccess() {
        DriverEntity user = createUser(new RegisterDTO("testpassword","test@email.com","test user"));
        Optional<DriverEntity> result = repository.findByEmail(user.getEmail());
        assertThat(result).isPresent();
        assertThat(result.get().getEmail()).isEqualTo(user.getEmail());
    }

    @Test
    @DisplayName("Should not get user by email")
    void findByEmailFailure() {
        Optional<DriverEntity> result = repository.findByEmail("nonexistent@email.com");
        assertThat(result).isEmpty();
    }

    @Test
    @DisplayName("Should get users by role with pagination")
    void findByRoleSuccess() {
        DriverEntity user = createUser(new RegisterDTO("testpassword","test@email.com","test user"));
        var pageable = Pageable.ofSize(10);
        var page = repository.findByRole(RoleEnum.CLIENT, pageable);
        assertThat(page.getTotalElements()).isEqualTo(1);
        assertThat(page.getContent().get(0).getRole()).isEqualTo(RoleEnum.CLIENT);
    }

    @Test
    @DisplayName("Should not get users by role")
    void findByRoleFailure() {
        var pageable = Pageable.ofSize(10);
        var page = repository.findByRole(RoleEnum.USER, pageable);
        assertThat(page.getTotalElements()).isEqualTo(0);
    }

    @Test
    @DisplayName("Should get all users with pagination")
    void findAllSuccess() {
        DriverEntity user = createUser(new RegisterDTO("testpassword","test@email.com","test user"));
        var pageable = Pageable.ofSize(10);
        var page = repository.findAll(pageable);
        assertThat(page.getTotalElements()).isEqualTo(1);
    }

    @Test
    @DisplayName("Should not get any user")
    void findAllFailure() {
        var pageable = Pageable.ofSize(10);
        var page = repository.findAll(pageable);
        assertThat(page.getTotalElements()).isEqualTo(0);
    }

    @Test
    @DisplayName("Should delete user by id")
    void deleteByIdSuccess() {
        DriverEntity user = createUser(new RegisterDTO("testpassword","test@email.com","test user"));
        UUID id = user.getId();
        Map<String,Object> result = repository.deleteById(id);
        assertThat(result).containsEntry("deleted", true);

        Optional<DriverEntity> deletedUser = repository.findById(id);
        assertThat(deletedUser).isEmpty();
    }

    @Test
    @DisplayName("Should not delete user by id")
    void deleteByIdFailure() {
        Map<String,Object> result = repository.deleteById(UUID.randomUUID());
        assertThat(result).containsEntry("deleted", false);
    }

    @Test
    @DisplayName("Should find user by id")
    void findByIdSuccess() {
        DriverEntity user = createUser(new RegisterDTO("testpassword","test@email.com","test user"));
        Optional<DriverEntity> result = repository.findById(user.getId());
        assertThat(result).isPresent();
    }

    @Test
    @DisplayName("Should not find user by id")
    void findByIdFailure() {
        Optional<DriverEntity> result = repository.findById(UUID.randomUUID());
        assertThat(result).isEmpty();
    }
}
