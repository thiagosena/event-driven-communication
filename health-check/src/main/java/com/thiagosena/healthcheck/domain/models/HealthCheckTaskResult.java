package com.thiagosena.healthcheck.domain.models;

import com.thiagosena.healthcheck.domain.models.valueobjects.HealthCheckConfig;
import com.thiagosena.healthcheck.domain.services.HealthResult;
import lombok.Builder;
import org.apache.commons.lang3.Validate;

import java.time.OffsetDateTime;
import java.util.UUID;

@Builder
public record HealthCheckTaskResult(
        UUID id,
        UUID taskId,
        UUID scheduleId,
        OffsetDateTime createdAt,
        HealthCheckConfig checkConfig,
        App app,
        HealthCheckTaskResult.Status status,
        Integer responseTime
) {
    public HealthCheckTaskResult {
        Validate.notNull(id);
        Validate.notNull(taskId);
        Validate.notNull(scheduleId);
        Validate.notNull(createdAt);
        Validate.notNull(checkConfig);
        Validate.notNull(app);
        Validate.notNull(status);
        Validate.notNull(responseTime);
    }

    public static HealthCheckTaskResult of(HealthCheckTask task, HealthResult result) {
        return HealthCheckTaskResult.builder()
                .id(UUID.randomUUID())
                .taskId(task.id())
                .createdAt(OffsetDateTime.now())
                .scheduleId(task.scheduleId())
                .checkConfig(task.checkConfig())
                .app(task.app())
                .status(result.status())
                .responseTime(result.responseTime())
                .build();
    }

    public enum Status {
        UP, DOWN, UNKNOWN
    }
}