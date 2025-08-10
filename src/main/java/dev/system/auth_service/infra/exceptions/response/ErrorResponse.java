package dev.system.auth_service.infra.exceptions.response;

public record ErrorResponse(
        int status,
        String error,
        String message,
        String path
) {
}
