package ru.projectpractic.service.impl;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.stereotype.Service;
import ru.projectpractic.dto.response.JobResponse;
import ru.projectpractic.entity.Employee;
import ru.projectpractic.exception.EntityNotFoundException;
import ru.projectpractic.repository.EmployeeRepository;
import ru.projectpractic.repository.JobRepository;
import ru.projectpractic.service.JobService;

import java.util.List;

@Service
public class JobServiceImpl implements JobService {
    private final JobRepository jobRepository;
    private final EmployeeRepository employeeRepository;
    private final ObjectMapper objectMapper;

    public JobServiceImpl(JobRepository jobRepository, EmployeeRepository employeeRepository, ObjectMapper objectMapper) {
        this.jobRepository = jobRepository;
        this.employeeRepository = employeeRepository;
        this.objectMapper = objectMapper;
    }

    @Override
    public JobResponse findById(Long id) {
        return objectMapper.convertValue(
                jobRepository.findById(id).orElseThrow(() -> new EntityNotFoundException("Job")),
                JobResponse.class
        );
    }

    @Override
    public List<JobResponse> findAll() {
        return jobRepository.findAll().stream()
                .map(job -> objectMapper.convertValue(job, JobResponse.class)).toList();
    }

    @Override
    public List<JobResponse> findAllByUserId(Long userId) {
        Employee employee = employeeRepository.findByUserId(userId).orElseThrow(() ->
                new EntityNotFoundException("Employee"));
        return jobRepository.findAllByEmployeeId(employee.getId()).stream()
                .map(job -> objectMapper.convertValue(job, JobResponse.class)).toList();
    }
}
