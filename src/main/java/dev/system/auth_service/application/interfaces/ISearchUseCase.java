package dev.system.auth_service.application.interfaces;

import java.util.Map;

public interface ISearchUseCase {
    Map<String, Object> run(String email, String role);
}
