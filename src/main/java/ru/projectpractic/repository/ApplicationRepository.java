package ru.projectpractic.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.projectpractic.entity.Application;

import java.util.List;

public interface ApplicationRepository extends JpaRepository<Application, Long> {
    List<Application> findAllByJobId(Long jobId);
}
