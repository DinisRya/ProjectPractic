package ru.projectpractic.controller;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import ru.projectpractic.dto.CustomExceptionDTO;
import ru.projectpractic.exception.*;

@ControllerAdvice
public class ExceptionControllerAdvice {
    private static final Logger LOGGER = LogManager.getLogger();
    private static final String LOG_STRING = "Exception type - (%s), Message - %s";

    @ExceptionHandler(Exception.class)
    @ResponseBody
    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    public CustomExceptionDTO exception(Exception e) {
        LOGGER.info(e.getMessage());
        return new CustomExceptionDTO(e.getMessage());
    }

    @ExceptionHandler(UserNotFoundException.class)
    @ResponseBody
    @ResponseStatus(HttpStatus.NOT_FOUND)
    public CustomExceptionDTO userNotFound(UserNotFoundException e) {
        LOGGER.info(String.format(LOG_STRING, e.getErrorType(), e.getMessage()));
        return new CustomExceptionDTO(e.getErrorType(), "Пользователь не найден");
    }

    @ExceptionHandler(UsernameAlreadyInUseException.class)
    @ResponseBody
    @ResponseStatus(HttpStatus.CONFLICT)
    public CustomExceptionDTO conflictUser(UsernameAlreadyInUseException e) {
        LOGGER.info(String.format(LOG_STRING, e.getErrorType(), e.getMessage()));
        return new CustomExceptionDTO(e.getErrorType(), "Username уже испльзуется другим пользователем");
    }

    @ExceptionHandler(EntityAlreadyExistsException.class)
    @ResponseBody
    @ResponseStatus(HttpStatus.CONFLICT)
    public CustomExceptionDTO entityAlreadyExists(EntityAlreadyExistsException e) {
        LOGGER.info(String.format(LOG_STRING, e.getErrorType(), e.getMessage()));
        return new CustomExceptionDTO(e.getErrorType(), e.getMessage());
    }

    @ExceptionHandler(EntityNotFoundException.class)
    @ResponseBody
    @ResponseStatus(HttpStatus.NOT_FOUND)
    public CustomExceptionDTO conflictUser(EntityNotFoundException e) {
        LOGGER.info(String.format(LOG_STRING, e.getErrorType(), e.getMessage()));
        return new CustomExceptionDTO(e.getErrorType(), e.getMessage());
    }

    @ExceptionHandler(ValidationException.class)
    @ResponseBody
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public CustomExceptionDTO validationException(ValidationException e) {
        LOGGER.info(String.format(LOG_STRING, e.getErrorType(), e.getMessage()));
        return new CustomExceptionDTO(e.getErrorType(), e.getMessage());
    }
}
