package ru.projectpractic.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.projectpractic.entity.Profile;

public interface ProfileRepository extends JpaRepository<Profile, Long> {
}
