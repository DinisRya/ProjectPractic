package ru.projectpractic.service.validator;

import ru.projectpractic.dto.request.EmployeeRequest;

public interface EmployeeValidator {
    void validateRequest(EmployeeRequest request);
}
