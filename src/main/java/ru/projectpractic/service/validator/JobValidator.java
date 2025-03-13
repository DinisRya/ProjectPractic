package ru.projectpractic.service.validator;

import ru.projectpractic.dto.request.JobRequest;

public interface JobValidator {
    void validateRequest(JobRequest request);
}
