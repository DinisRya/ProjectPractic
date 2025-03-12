package ru.projectpractic.exception;


import ru.projectpractic.utils.ExceptionType;

public class UsernameAlreadyInUseException extends CustomException {

    public UsernameAlreadyInUseException(String username) {
        super(ExceptionType.USERNAME_ALREADY_IN_USE,
                String.format("Пользователь с таким username = %s, уже существует", username));
    }
}
