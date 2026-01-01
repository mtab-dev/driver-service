package dev.system.driver_service.domain.enums;

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
