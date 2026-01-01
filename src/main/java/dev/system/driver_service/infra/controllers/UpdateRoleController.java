package dev.system.driver_service.infra.controllers;

import dev.system.driver_service.application.interfaces.IUpdateRoleUseCase;
import dev.system.driver_service.domain.dto.request.UpdateRoleDTO;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

@RestController
@RequestMapping("/auth")
public class UpdateRoleController {

    private final IUpdateRoleUseCase usecase;

    public UpdateRoleController(IUpdateRoleUseCase usecase) {
        this.usecase = usecase;
    }

    @PutMapping("/users/update/role")
    public ResponseEntity<Map<String, Object>> updateRole(@RequestBody UpdateRoleDTO dto) {
        return this.usecase.run(dto);
    }

}
