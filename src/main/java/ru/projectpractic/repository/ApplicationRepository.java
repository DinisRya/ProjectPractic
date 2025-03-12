package ru.projectpractic.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.projectpractic.entity.Application;

public interface ApplicationRepository extends JpaRepository<Application, Long> {
}
