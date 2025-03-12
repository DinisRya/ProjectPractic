package ru.projectpractic.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.projectpractic.entity.Employee;

public interface EmployeeRepository extends JpaRepository<Employee, Long> {
}
