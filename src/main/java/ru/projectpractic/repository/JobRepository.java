package ru.projectpractic.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.projectpractic.entity.Job;

public interface JobRepository extends JpaRepository<Job, Long> {
}
