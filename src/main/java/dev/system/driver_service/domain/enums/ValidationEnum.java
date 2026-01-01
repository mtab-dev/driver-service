package dev.system.driver_service.domain.enums;

public enum ValidationEnum {
    APPROVED("approved"),
    REJECTED("rejected"),
    PENDING("pending");

    private String validated;

    ValidationEnum(String validated){this.validated = validated;}

    public String getValidated() {
        return validated;
    }
}
