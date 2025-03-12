package ru.projectpractic.service.impl;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.stereotype.Service;
import ru.projectpractic.dto.response.JobResponse;
import ru.projectpractic.exception.EntityNotFoundException;
import ru.projectpractic.repository.JobRepository;
import ru.projectpractic.service.JobService;

import java.util.List;

@Service
public class JobServiceImpl implements JobService {
    private final JobRepository jobRepository;
    private final ObjectMapper objectMapper;

    public JobServiceImpl(JobRepository jobRepository, ObjectMapper objectMapper) {
        this.jobRepository = jobRepository;
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
}
