package dev.system.auth_service.domain.enums;

import lombok.Getter;
import lombok.Setter;

public enum RoleEnum {
    ADMIN("admin"),
    USER("user"),
    CLIENT("client");

    private String role;

     RoleEnum(String role) {
         this.role = role;
     }

     public String getRole() {
         return role;
     }
}
