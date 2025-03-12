package ru.projectpractic.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.projectpractic.entity.User;

public interface UserRepository extends JpaRepository<User, Long> {
}
