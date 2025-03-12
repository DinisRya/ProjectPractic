package ru.projectpractic.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.projectpractic.entity.User;

import java.util.Optional;

public interface UserRepository extends JpaRepository<User, Long> {
    Optional<User> findByUsername(String username);
}
