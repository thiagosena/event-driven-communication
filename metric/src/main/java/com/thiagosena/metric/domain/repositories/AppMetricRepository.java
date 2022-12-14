package com.thiagosena.metric.domain.repositories;

import com.thiagosena.metric.domain.models.AppMetric;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;
import java.util.UUID;

public interface AppMetricRepository extends JpaRepository<AppMetric, UUID> {
    Optional<AppMetric> findOneByAppId(UUID appId);
}