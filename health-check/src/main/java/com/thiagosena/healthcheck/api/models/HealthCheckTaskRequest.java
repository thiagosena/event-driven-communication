package com.thiagosena.healthcheck.api.models;

import com.thiagosena.healthcheck.domain.models.App;
import com.thiagosena.healthcheck.domain.models.HealthCheckTask;
import com.thiagosena.healthcheck.domain.models.valueobjects.HealthCheckConfig;

import java.time.OffsetDateTime;
import java.util.UUID;

public record HealthCheckTaskRequest(
        UUID id,
        AppModel app,
        HealthCheckConfigModel checkConfig,
        OffsetDateTime createdAt,
        UUID scheduleId
) {
    public HealthCheckTask toDomain() {
        HealthCheckConfigModel checkConfig = this.checkConfig();
        AppModel app = this.app();
        return HealthCheckTask.builder()
                .id(this.id())
                .createdAt(this.createdAt())
                .scheduleId(this.scheduleId())
                .checkConfig(HealthCheckConfig.builder()
                        .timeout(checkConfig.timeout())
                        .type(checkConfig.type())
                        .build())
                .app(App.builder()
                        .id(app.id())
                        .name(app.name())
                        .address(app.address())
                        .build())
                .build();
    }
}