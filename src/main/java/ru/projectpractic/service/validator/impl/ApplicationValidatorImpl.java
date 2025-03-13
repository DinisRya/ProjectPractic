package ru.projectpractic.service.validator.impl;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.stereotype.Component;
import ru.projectpractic.dto.request.ApplicationRequest;
import ru.projectpractic.exception.ValidationException;
import ru.projectpractic.service.validator.ApplicationValidator;
import ru.projectpractic.utils.ExceptionType;

@Component
public class ApplicationValidatorImpl implements ApplicationValidator {
    private final static Logger LOGGER = LogManager.getLogger();

    @Override
    public void validateRequest(ApplicationRequest request) {

        //username больше 100 символов
        if ((request.coverLetter().length() > 2055)) {
            final String message = "Username is too long (more than 100)";
            LOGGER.info(message);
            throw new ValidationException(ExceptionType.TOO_LONG_USERNAME, message);
        }
    }
}
