package ru.projectpractic.dto;

import ru.projectpractic.utils.ExceptionType;

public record CustomExceptionDTO(
        ExceptionType exceptionType,
        String message) {

    public CustomExceptionDTO(String message) {
        this(ExceptionType.ANY_EXCEPTION, message);
    }
}
