package ru.projectpractic.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.projectpractic.entity.Job;

import java.util.List;

public interface JobRepository extends JpaRepository<Job, Long> {
    List<Job> findAllByEmployeeId(Long id);
}
