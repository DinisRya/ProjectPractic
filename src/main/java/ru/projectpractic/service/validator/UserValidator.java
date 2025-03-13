package ru.projectpractic.service.validator;

import ru.projectpractic.dto.request.UserRequest;

public interface UserValidator {
    void validateRequest(UserRequest request);

    void validateUsername(String username);
}
