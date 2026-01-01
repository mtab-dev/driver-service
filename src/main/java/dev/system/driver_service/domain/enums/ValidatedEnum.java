package dev.system.driver_service.domain.enums;

public enum ValidatedEnum {
    APPROVED("approved"),
    REJECTED("rejected"),
    PENDING("pending");

    private String validated;

    ValidatedEnum(String validated){this.validated = validated;}

    public String getValidated() {
        return validated;
    }
}
