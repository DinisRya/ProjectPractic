package ru.projectpractic.dto.response;

import ru.projectpractic.utils.UserRoleEnum;

public record UserResponse(
        Long id,
        String username,
        String password,
        UserRoleEnum role
) {
}
