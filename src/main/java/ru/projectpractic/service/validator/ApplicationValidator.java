package ru.projectpractic.service.validator;

import ru.projectpractic.dto.request.ApplicationRequest;

public interface ApplicationValidator {
    void validateRequest(ApplicationRequest request);
}
