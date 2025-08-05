package dev.system.auth_service.infra.controllers;

import dev.system.auth_service.application.interfaces.ISearchUseCase;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/auth")
public class SearchController {

    private final ISearchUseCase usecase;

    public SearchController(ISearchUseCase usecase) {
        this.usecase = usecase;
    }

    @GetMapping("/users")
    public Map<String, Object> perform(
            @RequestParam(required = false) String email,
            @RequestParam(required = false) String role
    ){
        return usecase.run(email, role);
    }
}
