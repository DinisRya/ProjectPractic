package ru.projectpractic.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.projectpractic.entity.Employee;

import java.util.Optional;

public interface EmployeeRepository extends JpaRepository<Employee, Long> {
    Optional<Employee> findByUserId(Long userId);
}
