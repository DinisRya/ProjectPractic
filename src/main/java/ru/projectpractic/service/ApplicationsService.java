package ru.projectpractic.service;

import ru.projectpractic.dto.request.ApplicationRequest;
import ru.projectpractic.dto.response.ApplicationResponse;

import java.util.List;

public interface ApplicationsService {
    ApplicationResponse findById(Long id);

    List<ApplicationResponse> findAll();

    ApplicationResponse create(ApplicationRequest request);

    List<ApplicationResponse> findAllByJobId(Long jobId);

    void approve(Long id);

    void reject(Long id);
}
