package ru.projectpractic.service;

import ru.projectpractic.dto.request.StudentRequest;
import ru.projectpractic.dto.response.StudentResponse;

import java.util.List;

public interface StudentService {
    StudentResponse create(StudentRequest request);

    StudentResponse update(StudentRequest request, Long id);

    StudentResponse findById(Long id);

    List<StudentResponse> findAll();

    void delete(Long id);
}
