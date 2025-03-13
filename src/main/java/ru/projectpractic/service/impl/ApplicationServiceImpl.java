package ru.projectpractic.service.impl;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.stereotype.Service;
import ru.projectpractic.dto.request.ApplicationRequest;
import ru.projectpractic.dto.response.ApplicationResponse;
import ru.projectpractic.entity.Application;
import ru.projectpractic.exception.EntityNotFoundException;
import ru.projectpractic.repository.ApplicationRepository;
import ru.projectpractic.repository.JobRepository;
import ru.projectpractic.repository.StudentRepository;
import ru.projectpractic.service.ApplicationsService;
import ru.projectpractic.utils.ApplicationsStatusEnum;

import java.util.List;

@Service
public class ApplicationServiceImpl implements ApplicationsService {
    private final ApplicationRepository applicationRepository;
    private final ObjectMapper objectMapper;
    private final JobRepository jobRepository;
    private final StudentRepository studentRepository;

    public ApplicationServiceImpl(
            ApplicationRepository applicationRepository,
            ObjectMapper objectMapper,
            JobRepository jobRepository, StudentRepository studentRepository) {
        this.applicationRepository = applicationRepository;
        this.objectMapper = objectMapper;
        this.jobRepository = jobRepository;
        this.studentRepository = studentRepository;
    }

    @Override
    public ApplicationResponse findById(Long id) {
        return objectMapper.convertValue(applicationRepository.findById(id).orElseThrow(() ->
                new EntityNotFoundException("Application")), ApplicationResponse.class);
    }

    @Override
    public List<ApplicationResponse> findAll() {
        return applicationRepository.findAll().stream()
                .map(application -> objectMapper.convertValue(application, ApplicationResponse.class))
                .toList();
    }

    @Override
    public ApplicationResponse create(ApplicationRequest request) {
        Application application = new Application();
        application.setStatus(ApplicationsStatusEnum.WAITING);
        application.setJob(jobRepository.findById(request.jobId()).orElseThrow(() ->
                new EntityNotFoundException("Job")));
        application.setStudent(studentRepository.findById(request.studentId()).orElseThrow(() ->
                new EntityNotFoundException("Student")));
        return objectMapper.convertValue(applicationRepository.save(application), ApplicationResponse.class);
    }

    @Override
    public List<ApplicationResponse> findAllByJobId(Long jobId) {
        return applicationRepository.findAllByJobId(jobId).stream()
                .map(application -> objectMapper.convertValue(application, ApplicationResponse.class)).toList();
    }

    @Override
    public void approve(Long id) {
        var application = applicationRepository.findById(id).orElseThrow(() ->
                new EntityNotFoundException("Application"));
        application.setStatus(ApplicationsStatusEnum.APPROVED);
    }

    @Override
    public void reject(Long id) {
        var application = applicationRepository.findById(id).orElseThrow(() ->
                new EntityNotFoundException("Application"));
        application.setStatus(ApplicationsStatusEnum.REJECTED);
    }
}
