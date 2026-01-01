package dev.system.driver_service.domain.enums;

public enum StatusEnum {
    ACTIVE("active"),
    INACTIVE("inactive"),
    SUSPENDED("suspended"),
    BLOCKED("blocked");

    private String status;
    StatusEnum(String status) {this.status = status;}
    public String getStatus() {return status;}
}
