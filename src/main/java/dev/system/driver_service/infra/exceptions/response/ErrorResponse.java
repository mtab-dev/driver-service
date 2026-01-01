package dev.system.driver_service.infra.exceptions.response;

public record ErrorResponse(
        int status,
        String error,
        String message,
        String path
) {
}
