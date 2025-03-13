package ru.projectpractic.dto.request;

public record EmployeeRequest(
        String department,
        String organization,
        Long userId
) {
}
