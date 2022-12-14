package com.thiagosena.metric.api.models;

import com.thiagosena.metric.domain.models.HealthCheckTaskResult;

import java.time.OffsetDateTime;
import java.util.UUID;

public record HealthCheckTaskResultModel(
        UUID id,
        UUID taskId,
        UUID scheduleId,
        UUID appId,
        OffsetDateTime createdAt,
        HealthCheckConfigModel checkConfig,
        HealthCheckTaskResult.Status status,
        Integer responseTime
) {
    public HealthCheckTaskResult toDomain() {
        return HealthCheckTaskResult.builder()
                .id(this.id())
                .scheduleId(this.scheduleId())
                .appId(this.appId())
                .taskId(this.taskId())
                .createdAt(this.createdAt())
                .responseTime(this.responseTime())
                .status(this.status())
                .checkConfig(checkConfig.toDomain())
                .build();
    }
}