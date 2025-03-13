package ru.projectpractic.service.validator.impl;

import io.micrometer.common.util.StringUtils;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.stereotype.Component;
import ru.projectpractic.dto.request.UserRequest;
import ru.projectpractic.exception.ValidationException;
import ru.projectpractic.service.validator.UserValidator;
import ru.projectpractic.utils.ExceptionType;

@Component
public class UserValidatorImpl implements UserValidator {
    private static final Logger LOGGER = LogManager.getLogger();
    private static final String PASSWORD_PATTERN = "^(?=.*[A-Za-z])(?=.*\\d).{8,}$";

    @Override
    public void validateRequest(UserRequest request) {
        validateUsernameP(request.username());

        //Проверка на пустоту password
        if (request.password().isEmpty() || StringUtils.isBlank(request.password())) {
            final String message = "Password is empty or null";
            LOGGER.info(message);
            throw new ValidationException(ExceptionType.EMPTY_PASSWORD, message);
        }

        //password больше 100 символов
        if (request.password().length() > 100) {
            final String message = "Password is too long (more than 100)";
            LOGGER.info(message);
            throw new ValidationException(ExceptionType.TOO_LONG_PASSWORD, message);
        }

        //password меньше 5 символов
        if (request.password().length() < 8) {
            final String message = "Password is too short (less than 8)";
            LOGGER.info(message);
            throw new ValidationException(ExceptionType.TOO_SHORT_PASSWORD, message);
        }

        if (!request.password().matches(PASSWORD_PATTERN)) {
            final String message = "Password should have min 8 symbols, min 1 letter and min 1 num";
            LOGGER.info(message);
            throw new ValidationException(ExceptionType.PASSWORD_PATTERN_ERROR, message);
        }

    }

    @Override
    public void validateUsername(String username) {
        validateUsernameP(username);
    }

    private void validateUsernameP(String username) {
        //Проверка на пустоту username
        if (username.isEmpty() || StringUtils.isBlank(username)) {
            final String message = "Username is empty or null";
            LOGGER.info(message);
            throw new ValidationException(ExceptionType.EMPTY_USERNAME, message);
        }

        //username больше 100 символов
        if (username.length() > 100) {
            final String message = "Username is too long (more than 100)";
            LOGGER.info(message);
            throw new ValidationException(ExceptionType.TOO_LONG_USERNAME, message);
        }

        //username меньше 5 символов
        if (username.length() < 5) {
            final String message = "Username is too short (less than 5)";
            LOGGER.info(message);
            throw new ValidationException(ExceptionType.TOO_SHORT_USERNAME, message);
        }
    }
}
