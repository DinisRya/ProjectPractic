package ru.projectpractic.service;

import ru.projectpractic.dto.request.EmployeeRequest;
import ru.projectpractic.dto.response.EmployeeResponse;

public interface EmployeeService {
    EmployeeResponse create(EmployeeRequest request);

    EmployeeResponse update(EmployeeRequest request, Long employeeId);

    EmployeeResponse findById(Long id);

    EmployeeResponse findByUserId(Long userId);
}
