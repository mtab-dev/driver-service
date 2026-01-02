package dev.system.driver_service.infra.controllers;

import dev.system.driver_service.application.interfaces.ISearchUseCase;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

@RestController
@RequestMapping("/auth")
public class SearchController {

    private final ISearchUseCase usecase;

    public SearchController(ISearchUseCase usecase) {
        this.usecase = usecase;
    }

    @GetMapping("/driver")
    public Map<String, Object> perform(
            @RequestParam(required = false) String search,
            @RequestParam(required = false) String role,
            @RequestParam(defaultValue = "1") int page,
            @RequestParam(defaultValue = "10") int size
    ){
        return usecase.run(search, role, page, size);
    }
}
