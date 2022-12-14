package com.thiagosena.scheduler.domain.repositories;

import com.thiagosena.scheduler.domain.models.Schedule;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.UUID;

public interface ScheduleRepository extends JpaRepository<Schedule, UUID> {
    List<Schedule> findAllByRunInterval(Integer runInterval);
}