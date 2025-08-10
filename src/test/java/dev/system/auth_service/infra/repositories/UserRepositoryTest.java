package dev.system.auth_service.infra.repositories;

import dev.system.auth_service.application.interfaces.IUserRepository;
import dev.system.auth_service.domain.dto.request.RegisterDTO;
import dev.system.auth_service.domain.entities.UserEntity;
import dev.system.auth_service.domain.enums.RoleEnum;
import jakarta.persistence.EntityManager;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.context.annotation.Import;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.test.context.ActiveProfiles;

import java.util.List;
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

    private UserEntity createUser(RegisterDTO dto){

        String username = dto.name().trim();
        String encryptedPassword = new BCryptPasswordEncoder().encode(dto.password());

        UserEntity newUser = new UserEntity();
        newUser.setUsername(username);
        newUser.setPassword(encryptedPassword);
        newUser.setEmail(dto.email());
        newUser.setName(dto.name());
        newUser.setRole(RoleEnum.CLIENT);
        newUser.setActive(true);

        this.entityManager.persist(newUser);
        return newUser;
    }

    @Test
    @DisplayName("Should get user by username")
    void findByUsernameSuccess() {
        RegisterDTO dto = new RegisterDTO(
                "testpassword",
                "test@email.com",
                "test user"
        );

        UserEntity user = createUser(dto);

        String username = user.getUsername();

        UserDetails result = repository.findByUsername(username);

        assertThat(result.getUsername()).isEqualTo(username);
    }

    @Test
    @DisplayName("Should not get user by username")
    void findByUsernameFailure() {
        RegisterDTO dto = new RegisterDTO(
                "testpassword",
                "test@email.com",
                "test user"
        );

        UserEntity user = createUser(dto);

        String username = user.getUsername()+"test";

        UserDetails result = repository.findByUsername(username);

        assertThat(result).isNull();
    }

    @Test
    @DisplayName("Should get user by email")
    void findByEmailSuccess() {
        RegisterDTO dto = new RegisterDTO(
                "testpassword",
                "test@email.com",
                "test user"
        );

        UserEntity user = createUser(dto);

        Optional<UserEntity> result = repository.findByEmail(user.getEmail());

        assertThat(result.get().getEmail()).isEqualTo(user.getEmail());
    }

    @Test
    @DisplayName("Should not get user by email")
    void findByEmailFailure() {
        RegisterDTO dto = new RegisterDTO(
                "testpassword",
                "test@email.com",
                "test user"
        );

        UserEntity user = createUser(dto);

        Optional<UserEntity> result = repository.findByEmail(user.getEmail()+"test");

        assertThat(result).isEmpty();
    }

    @Test
    @DisplayName("Should get users by role")
    void findByRoleSuccess() {
        RegisterDTO dto = new RegisterDTO(
                "testpassword",
                "test@email.com",
                "test user"
        );
        UserEntity user = createUser(dto);
        List<UserEntity> result = repository.findByRole(RoleEnum.CLIENT);
        assertThat(result).hasSize(1);
    }

    @Test
    @DisplayName("Should not get users by role")
    void findByRoleFailure() {
        RegisterDTO dto = new RegisterDTO(
                "testpassword",
                "test@email.com",
                "test user"
        );
        UserEntity user = createUser(dto);
        List<UserEntity> result = repository.findByRole(RoleEnum.USER);
        assertThat(result).isEmpty();
    }

    @Test
    @DisplayName("Should get all users")
    void findAllSuccess() {
        RegisterDTO dto = new RegisterDTO(
                "testpassword",
                "test@email.com",
                "test user"
        );
        UserEntity user = createUser(dto);
        List<UserEntity> result = repository.findAll();
        assertThat(result).isNotNull();
    }

    @Test
    @DisplayName("Should not get any user")
    void findAllFailure() {
        List<UserEntity> result = repository.findAll();
        assertThat(result).isEmpty();
    }

    @Test
    @DisplayName("Should delete user by id")
    void deleteByIdSuccess() {
        RegisterDTO dto = new RegisterDTO(
                "testpassword",
                "test@email.com",
                "test user"
        );
        UserEntity user = createUser(dto);

        Optional<UserEntity> userToDelete = repository.findByEmail(user.getEmail());
        UUID id = userToDelete.get().getId();
        Map<String, Object> result = repository.deleteById(id);

        assertThat(result)
                .containsEntry("deleted", true);

        Optional<UserEntity> deletedUser = repository.findById(id);
        assertThat(deletedUser).isEmpty();
    }

    @Test
    @DisplayName("Should not delete user by id")
    void deleteByIdFailure() {
        Map<String, Object> result = repository.deleteById(UUID.randomUUID());
        assertThat(result)
                .containsEntry("deleted", false);
    }

    @Test
    @DisplayName("Should find user by id")
    void findByIdSuccess() {
        RegisterDTO dto = new RegisterDTO(
                "testpassword",
                "test@email.com",
                "test user"
        );
        UserEntity user = createUser(dto);
        var result =  repository.findById(user.getId());
        assertThat(result).isNotNull();
    }

    @Test
    @DisplayName("Should not find user by id")
    void findByIdFailure() {
        var result =  repository.findById(UUID.randomUUID());
        assertThat(result).isEmpty();
    }
}