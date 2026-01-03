package dev.system.driver_service.infra.security;

import dev.system.driver_service.application.interfaces.IUserRepository;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;
import java.util.List;
import java.util.UUID;

@Component
public class SecurityFilter extends OncePerRequestFilter {
    @Autowired
    TokenService tokenService;
    @Autowired
    IUserRepository repository;

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
        try{
            String token = this.recoverToken(request);

            if(token != null){

                System.out.println(token);

                var userId = tokenService.validateToken(token);

                if(userId == null){
                    filterChain.doFilter(request,response);
                    return;
                }

                UUID id = UUID.fromString(userId);

                var user = repository.findById(id)
                        .orElseThrow(() -> new UsernameNotFoundException("Invalid id"));

                var auth = new UsernamePasswordAuthenticationToken(
                        user,
                        null,
                       List.of()
                );

                SecurityContextHolder.getContext().setAuthentication(auth);

                System.out.println("Auth: " + auth.isAuthenticated());

            }

            filterChain.doFilter(request, response);

        }finally {
            SecurityContextHolder.clearContext();
        }
    }

    private String recoverToken(HttpServletRequest request){
        var authHeader = request.getHeader("Authorization");
        if(authHeader == null) return null;
        return authHeader.replace("Bearer ", "");
    }
}