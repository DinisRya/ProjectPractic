package ru.projectpractic.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.projectpractic.entity.Student;

public interface StudentRepository extends JpaRepository<Student, Long> {
}
