package ru.projectpractic.service.impl;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.stereotype.Service;
import ru.projectpractic.dto.request.JobRequest;
import ru.projectpractic.dto.response.JobResponse;
import ru.projectpractic.entity.Employee;
import ru.projectpractic.entity.Job;
import ru.projectpractic.exception.EntityNotFoundException;
import ru.projectpractic.repository.EmployeeRepository;
import ru.projectpractic.repository.JobRepository;
import ru.projectpractic.service.JobService;
import ru.projectpractic.service.validator.JobValidator;

import java.util.List;

@Service
public class JobServiceImpl implements JobService {
    private final JobRepository jobRepository;
    private final EmployeeRepository employeeRepository;
    private final ObjectMapper objectMapper;
    private final JobValidator jobValidator;

    public JobServiceImpl(JobRepository jobRepository, EmployeeRepository employeeRepository, ObjectMapper objectMapper, JobValidator jobValidator) {
        this.jobRepository = jobRepository;
        this.employeeRepository = employeeRepository;
        this.objectMapper = objectMapper;
        this.jobValidator = jobValidator;
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

    @Override
    public JobResponse create(JobRequest request) {
        jobValidator.validateRequest(request);

        Job job = new Job();
        job.setDescription(request.description());
        job.setEmployee(employeeRepository.findById(request.employeeId()).orElseThrow(() ->
                new EntityNotFoundException("Employee")));
        job.setTitle(request.title());
        return objectMapper.convertValue(jobRepository.save(job), JobResponse.class);
    }

    @Override
    public JobResponse update(JobRequest request, Long jobId) {
        jobValidator.validateRequest(request);

        Job job = jobRepository.findById(jobId).orElseThrow(() ->
                new EntityNotFoundException("Job"));
        job.setDescription(request.description());
        job.setEmployee(employeeRepository.findById(request.employeeId()).orElseThrow(() ->
                new EntityNotFoundException("Employee")));
        job.setTitle(request.title());
        return objectMapper.convertValue(jobRepository.save(job), JobResponse.class);
    }
}
