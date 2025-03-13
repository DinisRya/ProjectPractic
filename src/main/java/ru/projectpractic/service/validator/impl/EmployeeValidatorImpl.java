package ru.projectpractic.service.validator.impl;

import io.micrometer.common.util.StringUtils;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.stereotype.Component;
import ru.projectpractic.dto.request.EmployeeRequest;
import ru.projectpractic.exception.ValidationException;
import ru.projectpractic.service.validator.EmployeeValidator;
import ru.projectpractic.utils.ExceptionType;

@Component
public class EmployeeValidatorImpl implements EmployeeValidator {
    private static final Logger LOGGER = LogManager.getLogger();

    @Override
    public void validateRequest(EmployeeRequest request) {
        //Проверка на пустоту department
        if (request.department().isEmpty() || StringUtils.isBlank(request.department())) {
            final String message = "Department is empty or null";
            LOGGER.info(message);
            throw new ValidationException(ExceptionType.EMPTY_DEPARTMENT, message);
        }

        //department больше 100 символов
        if (request.department().length() > 100) {
            final String message = "Department is too long (more than 2055)";
            LOGGER.info(message);
            throw new ValidationException(ExceptionType.TOO_LONG_DEPARTMENT, message);
        }

        //department меньше 5 символов
        if (request.department().length() < 5) {
            final String message = "Department is too short (less than 5)";
            LOGGER.info(message);
            throw new ValidationException(ExceptionType.TOO_SHORT_DEPARTMENT, message);
        }

        //Проверка на пустоту organization
        if (request.organization().isEmpty() || StringUtils.isBlank(request.organization())) {
            final String message = "Organization is empty or null";
            LOGGER.info(message);
            throw new ValidationException(ExceptionType.EMPTY_ORGANIZATION, message);
        }

        //organization больше 2055 символов
        if (request.organization().length() > 2055) {
            final String message = "Organization is too long (more than 2055)";
            LOGGER.info(message);
            throw new ValidationException(ExceptionType.TOO_LONG_ORGANIZATION, message);
        }

        //organization меньше 10 символов
        if (request.organization().length() < 10) {
            final String message = "Organization is too short (less than 10)";
            LOGGER.info(message);
            throw new ValidationException(ExceptionType.TOO_SHORT_ORGANIZATION, message);
        }
    }
}
