package com.thiagosena.healthcheck.api.models;

import com.thiagosena.healthcheck.domain.models.valueobjects.HealthCheckConfig;
import com.thiagosena.healthcheck.domain.models.valueobjects.HealthCheckType;
import lombok.Builder;
import org.jetbrains.annotations.NotNull;

@Builder
public record HealthCheckConfigModel(
        @NotNull
        HealthCheckType type,
        @NotNull
        Integer timeout
) {
    public static HealthCheckConfigModel of(HealthCheckConfig checkConfig) {
        return HealthCheckConfigModel.builder()
                .type(checkConfig.type())
                .timeout(checkConfig.timeout())
                .build();
    }
}