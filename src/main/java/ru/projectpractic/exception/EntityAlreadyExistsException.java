package ru.projectpractic.exception;

import ru.projectpractic.utils.ExceptionType;

public class EntityAlreadyExistsException extends CustomException {

    public EntityAlreadyExistsException(String nameOfEntity) {
        super(ExceptionType.ENTITY_ALREADY_EXISTS, String.format("%s already exists", nameOfEntity));
    }
}
