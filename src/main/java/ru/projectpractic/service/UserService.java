package ru.projectpractic.service;

import ru.projectpractic.dto.request.UserRequest;
import ru.projectpractic.dto.response.UserResponse;

import java.util.List;

public interface UserService {
    UserResponse createStudent(UserRequest user);

    UserResponse createEmployee(UserRequest user);

    UserResponse update(UserRequest user, Long userId);

    List<UserResponse> findAll();

    UserResponse findById(Long id);

    void delete(Long id);

    UserResponse findByUsername(String username);
}
