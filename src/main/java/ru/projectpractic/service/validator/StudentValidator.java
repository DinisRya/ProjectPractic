package ru.projectpractic.service.validator;

import ru.projectpractic.dto.request.StudentRequest;

public interface StudentValidator {
    void validateRequest(StudentRequest request);
}
