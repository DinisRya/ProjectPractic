package ru.projectpractic.dto.response;

public record JobResponse(
        Long id,
        String description,
        String photo, //byte array
        String title,
        Long employeeId
) {
}
