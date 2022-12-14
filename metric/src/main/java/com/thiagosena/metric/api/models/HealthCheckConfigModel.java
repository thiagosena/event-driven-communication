package com.thiagosena.metric.api.models;

import com.thiagosena.metric.domain.models.HealthCheckConfig;
import org.jetbrains.annotations.NotNull;

public record HealthCheckConfigModel(
        @NotNull
        HealthCheckConfig.CheckType type,
        @NotNull
        Integer timeout

) {
    public HealthCheckConfig toDomain() {
        return HealthCheckConfig.builder()
                .type(this.type())
                .timeout(this.timeout())
                .build();
    }
}