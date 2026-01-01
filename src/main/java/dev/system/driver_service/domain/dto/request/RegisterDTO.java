package dev.system.driver_service.domain.dto.request;

import java.time.LocalDateTime;

public record RegisterDTO(
        String password,
        String email,
        String name,
        LocalDateTime birthDate,
        String cpf,
        String rg,
        String cnh,
        String phone
        ) {
}
