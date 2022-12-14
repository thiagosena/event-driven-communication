package com.thiagosena.metric.api.models;

import com.thiagosena.metric.domain.models.AppMetricEvent;
import com.thiagosena.metric.domain.models.HealthCheckConfig;
import com.thiagosena.metric.domain.models.HealthCheckTaskResult;
import lombok.Builder;

import java.time.OffsetDateTime;
import java.util.UUID;

@Builder
public record AppMetricEventModel(
        UUID id,
        UUID scheduleId,
        UUID appId,
        OffsetDateTime createdAt,
        HealthCheckConfig.CheckType checkType,
        HealthCheckTaskResult.Status resultStatus
) {
    public static AppMetricEventModel of(AppMetricEvent appMetricEvent) {
        return AppMetricEventModel.builder()
                .id(appMetricEvent.getId())
                .scheduleId(appMetricEvent.getScheduleId())
                .appId(appMetricEvent.getAppId())
                .createdAt(appMetricEvent.getCreatedAt())
                .checkType(appMetricEvent.getCheckType())
                .resultStatus(appMetricEvent.getResultStatus())
                .build();
    }
}