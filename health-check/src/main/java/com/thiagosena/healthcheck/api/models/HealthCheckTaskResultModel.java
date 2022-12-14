package com.thiagosena.healthcheck.api.models;

import com.thiagosena.healthcheck.domain.models.HealthCheckTaskResult;
import lombok.Builder;

import java.time.OffsetDateTime;
import java.util.UUID;

@Builder
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
    public static HealthCheckTaskResultModel of(HealthCheckTaskResult result) {
        return HealthCheckTaskResultModel.builder()
                .id(result.id())
                .taskId(result.taskId())
                .scheduleId(result.scheduleId())
                .appId(result.app().id())
                .createdAt(result.createdAt())
                .checkConfig(HealthCheckConfigModel.of(result.checkConfig()))
                .status(result.status())
                .responseTime(result.responseTime())
                .build();
    }
}