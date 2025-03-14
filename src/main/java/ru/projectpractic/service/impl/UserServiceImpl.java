package ru.projectpractic.service.impl;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import ru.projectpractic.dto.request.UserRequest;
import ru.projectpractic.dto.response.UserResponse;
import ru.projectpractic.entity.Employee;
import ru.projectpractic.entity.Student;
import ru.projectpractic.entity.User;
import ru.projectpractic.exception.EntityNotFoundException;
import ru.projectpractic.exception.UserNotFoundException;
import ru.projectpractic.exception.UsernameAlreadyInUseException;
import ru.projectpractic.repository.EmployeeRepository;
import ru.projectpractic.repository.StudentRepository;
import ru.projectpractic.repository.UserRepository;
import ru.projectpractic.service.StudentService;
import ru.projectpractic.service.UserService;
import ru.projectpractic.service.validator.UserValidator;
import ru.projectpractic.utils.UserRoleEnum;

import java.util.List;

@Service
public class UserServiceImpl implements UserService {
    private static final Logger LOGGER = LoggerFactory.getLogger(UserServiceImpl.class);
    private final UserRepository userRepository;
    private final ObjectMapper objectMapper;
    private final PasswordEncoder passwordEncoder;
    private final StudentRepository studentRepository;
    private final EmployeeRepository employeeRepository;
    private final UserValidator userValidator;

    public UserServiceImpl(UserRepository userRepository, ObjectMapper objectMapper, PasswordEncoder passwordEncoder, StudentRepository studentRepository, EmployeeRepository employeeRepository, UserValidator userValidator) {
        this.userRepository = userRepository;
        this.objectMapper = objectMapper;
        this.passwordEncoder = passwordEncoder;
        this.studentRepository = studentRepository;
        this.employeeRepository = employeeRepository;
        this.userValidator = userValidator;
    }

    @Override
    public UserResponse createStudent(UserRequest user) {
        userValidator.validateRequest(user);

        if (userRepository.findByUsername(user.username()).isPresent()) {
            throw new UsernameAlreadyInUseException(user.username());
        }
        var student = createUser(user);
        student.setRole(UserRoleEnum.STUDENT);
        User savedUser = userRepository.save(student);
        LOGGER.info(savedUser.toString());
        setStudent(savedUser);
        return objectMapper.convertValue(savedUser, UserResponse.class);
    }

    private void setStudent(User user) {
        Student student = new Student();
        student.setUser(user);
        studentRepository.save(student);
    }

    @Override
    public UserResponse createEmployee(UserRequest user) {
        userValidator.validateRequest(user);

        if (userRepository.findByUsername(user.username()).isPresent()) {
            throw new UsernameAlreadyInUseException(user.username());
        }
        var employee = createUser(user);
        employee.setRole(UserRoleEnum.EMPLOYEE);
        User savedUser = userRepository.save(employee);
        LOGGER.info(savedUser.toString());
        setEmployee(savedUser);
        return objectMapper.convertValue(employee, UserResponse.class);
    }

    private void setEmployee(User user) {
        Employee employee = new Employee();
        employee.setUser(user);
        employeeRepository.save(employee);
    }

    private User createUser(UserRequest request) {
        userValidator.validateRequest(request);

        var userEntity = new User();
        userEntity.setUsername(request.username());
        userEntity.setPassword(passwordEncoder.encode(request.password()));
        userEntity.setEnabled(true);
        return userEntity;
    }

    @Override
    public UserResponse update(UserRequest user, Long userId) {
        userValidator.validateRequest(user);

        var userResponse = findById(userId);

        return objectMapper.convertValue(userRepository.save(
                objectMapper.convertValue(userResponse, User.class)),
                UserResponse.class
        );
    }

    @Override
    public List<UserResponse> findAll() {
        return userRepository.findAll().stream()
                .map(user -> objectMapper.convertValue(user, UserResponse.class)).toList();
    }

    @Override
    public UserResponse findById(Long id) {
        return objectMapper.convertValue(userRepository.findById(id).orElseThrow(() ->
                new UserNotFoundException(id)), UserResponse.class);
    }

    @Override
    public void delete(Long id) {
        var user = findById(id);
        userRepository.delete(objectMapper.convertValue(user, User.class));
    }

    @Override
    public UserResponse findByUsername(String username) {
        userValidator.validateUsername(username);

        var user = userRepository.findByUsername(username);
        if (user.isEmpty()) {
            throw new EntityNotFoundException("User");
        } else {
            return objectMapper.convertValue(user.get(), UserResponse.class);
        }
    }
}
