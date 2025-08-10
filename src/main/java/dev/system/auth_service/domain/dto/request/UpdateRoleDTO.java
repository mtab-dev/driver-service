package dev.system.auth_service.domain.dto.request;

import java.util.UUID;

public record UpdateRoleDTO(UUID id, String role) {
}
