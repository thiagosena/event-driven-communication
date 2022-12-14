package com.thiagosena.scheduler.api.models;

import com.thiagosena.scheduler.domain.models.valueobject.HealthCheckConfig;
import lombok.Builder;
import org.jetbrains.annotations.NotNull;

@Builder
public record HealthCheckConfigModel(
        @NotNull
        HealthCheckConfig.Type type,
        @NotNull
        Integer timeout
) {
    public static HealthCheckConfigModel of(HealthCheckConfig checkConfig) {
        return HealthCheckConfigModel.builder()
                .type(checkConfig.getType())
                .timeout(checkConfig.getTimeout())
                .build();
    }
}