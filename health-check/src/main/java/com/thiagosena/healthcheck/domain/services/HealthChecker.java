package com.thiagosena.healthcheck.domain.services;

import com.thiagosena.healthcheck.domain.models.HealthCheckTask;

public interface HealthChecker {
    HealthResult execute(HealthCheckTask task);
}