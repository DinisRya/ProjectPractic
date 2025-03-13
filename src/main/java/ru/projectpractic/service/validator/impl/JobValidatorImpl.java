package ru.projectpractic.service.validator.impl;

import io.micrometer.common.util.StringUtils;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.stereotype.Component;
import ru.projectpractic.dto.request.JobRequest;
import ru.projectpractic.exception.ValidationException;
import ru.projectpractic.service.validator.JobValidator;
import ru.projectpractic.utils.ExceptionType;

@Component
public class JobValidatorImpl implements JobValidator {
    private final static Logger LOGGER = LogManager.getLogger();

    @Override
    public void validateRequest(JobRequest request) {
        //Проверка на пустоту description
        if (request.description().isEmpty() || StringUtils.isBlank(request.description())) {
            final String message = "Description is empty or null";
            LOGGER.info(message);
            throw new ValidationException(ExceptionType.EMPTY_DESCRIPTION, message);
        }

        //description больше 2055 символов
        if (request.description().length() > 2055) {
            final String message = "Description is too long (more than 2055)";
            LOGGER.info(message);
            throw new ValidationException(ExceptionType.TOO_LONG_DESCRIPTION, message);
        }

        //description меньше 10 символов
        if (request.description().length() < 10) {
            final String message = "Description is too short (less than 10)";
            LOGGER.info(message);
            throw new ValidationException(ExceptionType.TOO_SHORT_DESCRIPTION, message);
        }

        //Проверка на пустоту title
        if (request.title().isEmpty() || StringUtils.isBlank(request.title())) {
            final String message = "Title is empty or null";
            LOGGER.info(message);
            throw new ValidationException(ExceptionType.EMPTY_TITLE, message);
        }

        //title больше 100 символов
        if (request.title().length() > 100) {
            final String message = "Title is too long (more than 100)";
            LOGGER.info(message);
            throw new ValidationException(ExceptionType.TOO_LONG_TITLE, message);
        }

        //title меньше 10 символов
        if (request.title().length() < 10) {
            final String message = "Title is too short (less than 10)";
            LOGGER.info(message);
            throw new ValidationException(ExceptionType.TOO_SHORT_TITLE, message);
        }
    }
}
