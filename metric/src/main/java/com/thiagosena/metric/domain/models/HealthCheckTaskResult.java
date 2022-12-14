package com.thiagosena.metric.domain.models;

import lombok.Builder;
import org.apache.commons.lang3.Validate;

import javax.persistence.Column;
import javax.persistence.Embedded;
import java.time.OffsetDateTime;
import java.util.UUID;

@Builder
public record HealthCheckTaskResult(
        @Column(name = "id", columnDefinition = "varchar(36)") UUID id,
        @Column(columnDefinition = "varchar(36)") UUID taskId,
        @Column(columnDefinition = "varchar(36)") UUID scheduleId, OffsetDateTime createdAt,
        @Embedded HealthCheckConfig checkConfig,
        Status status,
        Integer responseTime, @Column(columnDefinition = "varchar(36)") UUID appId
) {
    public HealthCheckTaskResult {
        Validate.notNull(id);
        Validate.notNull(taskId);
        Validate.notNull(scheduleId);
        Validate.notNull(createdAt);
        Validate.notNull(checkConfig);
        Validate.notNull(status);
        Validate.notNull(responseTime);
        Validate.notNull(appId);
    }

    public enum Status {
        UP, DOWN, UNKNOWN
    }

}