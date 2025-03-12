package ru.projectpractic.exception;

import ru.projectpractic.utils.ExceptionType;

public class CustomException extends RuntimeException{
    private ExceptionType exceptionType;
    private String message;

    public CustomException(ExceptionType exceptionType, String message) {
        this.exceptionType = exceptionType;
        this.message = message;
    }

    public CustomException(String message) {
        this.message = message;
    }

    public ExceptionType getErrorType() {
        return exceptionType;
    }

    public void setErrorType(ExceptionType exceptionType) {
        this.exceptionType = exceptionType;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public static CustomException create(Throwable e) {
        return new CustomException(e.getMessage());
    }

    public static CustomException create(String message) {
            return new CustomException(message);
        }
}
