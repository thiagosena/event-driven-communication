package com.thiagosena.healthcheck.domain.models.valueobjects;

import lombok.Builder;

@Builder
public record HealthCheckConfig(HealthCheckType type, Integer timeout) {
}