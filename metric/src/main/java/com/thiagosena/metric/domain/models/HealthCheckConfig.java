package com.thiagosena.metric.domain.models;

import lombok.Builder;
import org.apache.commons.lang3.Validate;

@Builder
public record HealthCheckConfig(CheckType type, Integer timeout) {
    public HealthCheckConfig {
        Validate.notNull(type);
        Validate.notNull(timeout);
    }

    public enum CheckType {
        HTTP,
        PING;
    }
}