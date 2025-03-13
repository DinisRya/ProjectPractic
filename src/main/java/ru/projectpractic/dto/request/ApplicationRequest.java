package ru.projectpractic.dto.request;

public record ApplicationRequest(
        String coverLetter,
        Long jobId,
        Long userId
) {

}
