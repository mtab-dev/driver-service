package dev.system.driver_service.domain.dto.request;

import java.util.UUID;

public record UpdateDriverDTO(
        UUID id,
        String name,
        String email,
        String password
        ) {
}
