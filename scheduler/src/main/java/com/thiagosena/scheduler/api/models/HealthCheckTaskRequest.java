package com.thiagosena.scheduler.api.models;

import com.thiagosena.scheduler.domain.models.HealthCheckTask;
import lombok.Builder;

import java.time.OffsetDateTime;
import java.util.UUID;

@Builder
public record HealthCheckTaskRequest(
        UUID id,
        AppModel app,
        HealthCheckConfigModel checkConfig,
        OffsetDateTime createdAt,
        UUID scheduleId
) {
    public static HealthCheckTaskRequest of(HealthCheckTask task) {
        return HealthCheckTaskRequest.builder()
                .id(task.id())
                .app(AppModel.of(task.app()))
                .checkConfig(HealthCheckConfigModel.of(task.checkConfig()))
                .createdAt(task.createdAt())
                .scheduleId(task.scheduleId())
                .build();
    }
}