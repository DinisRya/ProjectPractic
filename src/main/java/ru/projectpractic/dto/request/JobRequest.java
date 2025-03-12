package ru.projectpractic.dto.request;

public record JobRequest(
        String description,
        String photo,
        String title,
        Long employeeId
) {
}
