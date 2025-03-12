package ru.projectpractic.dto.response;

public record StudentResponse(
        Long id,
        Integer courseOfStudy,
        String faculty,
        String resume,
        Long userId
) {
}
