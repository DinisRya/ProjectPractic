package ru.projectpractic.service.impl;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.stereotype.Service;
import ru.projectpractic.dto.request.EmployeeRequest;
import ru.projectpractic.dto.response.EmployeeResponse;
import ru.projectpractic.entity.Employee;
import ru.projectpractic.exception.EntityNotFoundException;
import ru.projectpractic.exception.UserNotFoundException;
import ru.projectpractic.repository.EmployeeRepository;
import ru.projectpractic.repository.UserRepository;
import ru.projectpractic.service.EmployeeService;

@Service
public class EmployeeServiceImpl implements EmployeeService {
    private final EmployeeRepository employeeRepository;
    private final ObjectMapper objectMapper;
    private final UserRepository userRepository;

    public EmployeeServiceImpl(EmployeeRepository employeeRepository, ObjectMapper objectMapper, UserRepository userRepository) {
        this.employeeRepository = employeeRepository;
        this.objectMapper = objectMapper;
        this.userRepository = userRepository;
    }

    @Override
    public EmployeeResponse create(EmployeeRequest request) {
        Employee employee = new Employee();
        employee.setDepartment(request.department());
        employee.setOrganization(request.organization());
        employee.setUser(userRepository.findById(request.userId()).orElseThrow(() ->
                new UserNotFoundException(request.userId())));
        return objectMapper.convertValue(employeeRepository.save(employee), EmployeeResponse.class);
    }

    @Override
    public EmployeeResponse update(EmployeeRequest request, Long employeeId) {
        Employee employee = employeeRepository.findById(employeeId).orElseThrow(() ->
                new EntityNotFoundException("Employee"));
        employee.setDepartment(request.department());
        employee.setOrganization(request.organization());
        employee.setUser(userRepository.findById(request.userId()).orElseThrow(() ->
                new UserNotFoundException(request.userId())));
        return objectMapper.convertValue(employeeRepository.save(employee), EmployeeResponse.class);
    }

    @Override
    public EmployeeResponse findById(Long id) {
        return objectMapper.convertValue(employeeRepository.findById(id).orElseThrow(() ->
                new EntityNotFoundException("Employee")), EmployeeResponse.class);
    }

    @Override
    public EmployeeResponse findByUserId(Long userId) {
        return objectMapper.convertValue(
                employeeRepository.findByUserId(userId).orElseThrow(() ->
                        new EntityNotFoundException("Employee")),
                EmployeeResponse.class
        );
    }
}
