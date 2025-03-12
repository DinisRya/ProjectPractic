package ru.projectpractic.dto.request;

public record StudentRequest(
        Integer courseOfStudy,
        String faculty,
        String resume,
        Long userId
) {
}
