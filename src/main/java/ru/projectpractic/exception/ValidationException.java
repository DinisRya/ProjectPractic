package ru.projectpractic.exception;

import ru.projectpractic.utils.ExceptionType;

public class ValidationException extends CustomException{

    public ValidationException(ExceptionType exceptionType, String message) {
        super(exceptionType, message);
    }

    public ValidationException(String message) {
        super(message);
    }
}
