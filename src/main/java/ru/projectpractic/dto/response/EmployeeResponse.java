package ru.projectpractic.dto.response;

public record EmployeeResponse(
        Long id,
        String department,
        String organization,
        Long userId
) {
}
