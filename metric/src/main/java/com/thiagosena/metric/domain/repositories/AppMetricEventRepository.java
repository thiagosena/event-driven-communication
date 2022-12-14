package com.thiagosena.metric.domain.repositories;

import com.thiagosena.metric.domain.models.AppMetricEvent;
import com.thiagosena.metric.domain.models.HealthCheckConfig;
import org.springframework.data.jpa.repository.JpaRepository;

import java.time.OffsetDateTime;
import java.util.Optional;
import java.util.UUID;

public interface AppMetricEventRepository extends JpaRepository<AppMetricEvent, UUID> {
    Optional<AppMetricEvent> findFirstByCreatedAtBeforeAndCheckTypeAndIdNotOrderByCreatedAtDesc(OffsetDateTime createdAt,
                                                                                                HealthCheckConfig.CheckType type,
                                                                                                UUID id);
}