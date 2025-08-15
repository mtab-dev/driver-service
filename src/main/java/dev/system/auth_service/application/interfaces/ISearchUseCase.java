package dev.system.auth_service.application.interfaces;

import java.util.Map;

public interface ISearchUseCase {
    Map<String, Object> run(String search, String role, int page, int size);
}
