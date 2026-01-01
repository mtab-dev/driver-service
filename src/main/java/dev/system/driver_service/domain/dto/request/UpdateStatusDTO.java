package dev.system.driver_service.domain.dto.request;

import java.util.UUID;

public record UpdateStatusDTO(UUID id, String status) {
}
