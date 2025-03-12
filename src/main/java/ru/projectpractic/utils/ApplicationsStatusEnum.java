package ru.projectpractic.utils;

public enum ApplicationsStatusEnum {
    WAITING("WAITING"),
    APPROVED("APPROVED"),
    REJECTED("REJECTED");

    private String value;

    ApplicationsStatusEnum(String value) {
        this.value = value;
    }

    public String getValue() {
        return value;
    }
}
