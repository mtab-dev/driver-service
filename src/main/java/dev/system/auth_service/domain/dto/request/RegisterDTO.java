package dev.system.auth_service.domain.dto.request;

public record RegisterDTO(String password, String email, String name) {
}
