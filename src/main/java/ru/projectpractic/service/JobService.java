package ru.projectpractic.service;

import ru.projectpractic.dto.request.JobRequest;
import ru.projectpractic.dto.response.JobResponse;
import ru.projectpractic.entity.Job;

import java.util.List;

public interface JobService {
    JobResponse findById(Long id);

    List<JobResponse> findAll();

    List<JobResponse> findAllByUserId(Long userId);

    JobResponse create(JobRequest request);

    JobResponse update(JobRequest request, Long jobId);
}
