package dev.system.auth_service.application.usecases;

import dev.system.auth_service.application.interfaces.IUpdateRoleUseCase;
import dev.system.auth_service.application.interfaces.IUserRepository;
import dev.system.auth_service.domain.dto.request.UpdateRoleDTO;
import dev.system.auth_service.domain.enums.RoleEnum;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.Map;
import java.util.UUID;

@Service
public class UpdateRoleUseCase implements IUpdateRoleUseCase {

    private final IUserRepository repository;

    public  UpdateRoleUseCase(IUserRepository repository) {
        this.repository = repository;
    }

    @Override
    public ResponseEntity<Map<String, Object>> run(UpdateRoleDTO dto) {
        var userToUpdate = repository.findById(dto.id());

        if(userToUpdate.isEmpty()) return ResponseEntity.notFound().build();

        var user = userToUpdate.get();

        user.setRole(RoleEnum.valueOf(dto.role()));
        user.setUpdatedAt(LocalDateTime.now());

        var response = repository.save(user);

        return ResponseEntity.ok(response);
    }
}
