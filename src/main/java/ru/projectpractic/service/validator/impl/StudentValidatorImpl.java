package ru.projectpractic.service.validator.impl;

import io.micrometer.common.util.StringUtils;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.stereotype.Component;
import ru.projectpractic.dto.request.StudentRequest;
import ru.projectpractic.exception.ValidationException;
import ru.projectpractic.service.validator.StudentValidator;
import ru.projectpractic.utils.ExceptionType;

@Component
public class StudentValidatorImpl implements StudentValidator {
    private final static Logger LOGGER = LogManager.getLogger();

    @Override
    public void validateRequest(StudentRequest request) {
        if (request.courseOfStudy() == null) {
            final String message = "Course of study is empty or null";
            LOGGER.info(message);
            throw new ValidationException(ExceptionType.COURSE_OF_STUDY_EMPTY_ERROR, message);
        }

        if (request.courseOfStudy() > 6 || request.courseOfStudy() < 1) {
            final String message = "University has only 6 courses";
            LOGGER.info(message);
            throw new ValidationException(ExceptionType.COURSE_OF_STUDY_VALUE_ERROR, message);
        }


        //Проверка на пустоту faculty
        if (request.faculty().isEmpty() || StringUtils.isBlank(request.faculty())) {
            final String message = "Faculty is empty or null";
            LOGGER.info(message);
            throw new ValidationException(ExceptionType.EMPTY_FACULTY, message);
        }

        //faculty больше 100 символов
        if (request.faculty().length() > 100) {
            final String message = "Faculty is too long (more than 100)";
            LOGGER.info(message);
            throw new ValidationException(ExceptionType.TOO_LONG_FACULTY, message);
        }

        //faculty меньше 5 символов
        if (request.faculty().length() < 5) {
            final String message = "Faculty is too short (less than 5)";
            LOGGER.info(message);
            throw new ValidationException(ExceptionType.TOO_SHORT_FACULTY, message);
        }

        //Проверка на пустоту resume
        if (request.resume().isEmpty() || StringUtils.isBlank(request.resume())) {
            final String message = "Resume is empty or null";
            LOGGER.info(message);
            throw new ValidationException(ExceptionType.EMPTY_RESUME, message);
        }

        //resume больше 100 символов
        if (request.resume().length() > 100) {
            final String message = "Resume is too long (more than 100)";
            LOGGER.info(message);
            throw new ValidationException(ExceptionType.TOO_LONG_RESUME, message);
        }

        //resume меньше 5 символов
        if (request.resume().length() < 5) {
            final String message = "Resume is too short (less than 5)";
            LOGGER.info(message);
            throw new ValidationException(ExceptionType.TOO_SHORT_RESUME, message);
        }
    }
}
