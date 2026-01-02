package dev.system.driver_service.domain.dto.request;

import java.time.LocalDate;

public record RegisterDTO(
        String password,
        String email,
        String name,
        LocalDate birthDate,
        String cpf,
        String rg,
        String cnh,
        String phone
        ) {
}
