package ru.projectpractic.utils;

public enum UserRoleEnum {
    STUDENT("STUDENT"),
    EMPLOYEE("EMPLOYEE"),
    ADMIN("ADMIN");

    private String value;

    UserRoleEnum(String value) {
        this.value = value;
    }

    public String getValue() {
        return value;
    }
}
