package dev.system.auth_service.domain.dto.request;

import java.util.UUID;

public record UpdateUserDTO(
        UUID id,
        String name,
        String email,
        String password
        ) {
}
