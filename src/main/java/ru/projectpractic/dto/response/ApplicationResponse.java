package ru.projectpractic.dto.response;

import ru.projectpractic.utils.ApplicationsStatusEnum;

public record ApplicationResponse(
        Long id,
        String coverLetter,
        ApplicationsStatusEnum status,
        Long jobId,
        Long studentId
) {
}
