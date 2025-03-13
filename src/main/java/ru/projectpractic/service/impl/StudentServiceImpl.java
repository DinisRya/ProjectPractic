package ru.projectpractic.service.impl;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.stereotype.Service;
import ru.projectpractic.dto.request.StudentRequest;
import ru.projectpractic.dto.response.StudentResponse;
import ru.projectpractic.entity.Student;
import ru.projectpractic.exception.EntityNotFoundException;
import ru.projectpractic.exception.UserNotFoundException;
import ru.projectpractic.repository.StudentRepository;
import ru.projectpractic.repository.UserRepository;
import ru.projectpractic.service.StudentService;

import java.util.List;

@Service
public class StudentServiceImpl implements StudentService {
    private final StudentRepository studentRepository;
    private final ObjectMapper objectMapper;
    private final UserRepository userRepository;

    public StudentServiceImpl(
            StudentRepository studentRepository,
            ObjectMapper objectMapper, UserRepository userRepository) {
        this.studentRepository = studentRepository;
        this.objectMapper = objectMapper;
        this.userRepository = userRepository;
    }

    @Override
    public StudentResponse create(StudentRequest request) {
        var student = new Student();

        return objectMapper.convertValue(
                studentRepository.save(setValues(request, student)),
                StudentResponse.class);
    }

    private Student setValues(StudentRequest request, Student student) {
        student.setUser(userRepository.findById(request.userId()).orElseThrow(() ->
                new UserNotFoundException(request.userId())));
        student.setCourseOfStudy(request.courseOfStudy());
        student.setFaculty(request.faculty());
        student.setResume(request.resume());
        return student;
    }

    @Override
    public StudentResponse update(StudentRequest request, Long id) {
        var student = studentRepository.findById(id).orElseThrow(() ->
                new EntityNotFoundException("Student"));

        return objectMapper.convertValue(studentRepository.save(setValues(request, student)), StudentResponse.class);
    }

    @Override
    public StudentResponse findById(Long id) {
        return objectMapper.convertValue(
                studentRepository.findById(id).orElseThrow(() ->
                        new EntityNotFoundException("Student")),
                StudentResponse.class
        );
    }

    @Override
    public List<StudentResponse> findAll() {
        return studentRepository
                .findAll().stream()
                .map(student -> objectMapper.convertValue(student, StudentResponse.class))
                .toList();
    }

    @Override
    public void delete(Long id) {
        //validate exists
        studentRepository.deleteById(id);
    }

    @Override
    public StudentResponse findByUserId(Long userId) {
        return objectMapper.convertValue(studentRepository.findByUserId(userId)
                .orElseThrow(() -> new EntityNotFoundException("Student")), StudentResponse.class);
    }
}
