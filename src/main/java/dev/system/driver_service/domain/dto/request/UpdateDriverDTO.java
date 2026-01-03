package dev.system.driver_service.domain.dto.request;


public record UpdateDriverDTO(
        String name,
        String email,
        String password
        ) {
}
