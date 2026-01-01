package dev.system.driver_service.domain.dto.request;

import java.util.UUID;

public record UpdateValidationDTO(UUID id, String validation) {
}
