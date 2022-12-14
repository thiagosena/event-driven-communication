package com.thiagosena.healthcheck.domain.models.valueobjects;

import com.thiagosena.healthcheck.domain.services.HealthChecker;
import com.thiagosena.healthcheck.infra.HttpHealthChecker;
import com.thiagosena.healthcheck.infra.PingHealthChecker;
import lombok.AllArgsConstructor;

@AllArgsConstructor

public enum HealthCheckType {
    HTTP(new HttpHealthChecker()),
    PING(new PingHealthChecker());

    private final HealthChecker healthChecker;

    public HealthChecker getHealthChecker() {
        return healthChecker;
    }
}