package com.thiagosena.healthcheck.domain.services;

import com.thiagosena.healthcheck.domain.models.HealthCheckTaskResult;
import lombok.Builder;

@Builder
public record HealthResult(HealthCheckTaskResult.Status status, Integer responseTime) {
}