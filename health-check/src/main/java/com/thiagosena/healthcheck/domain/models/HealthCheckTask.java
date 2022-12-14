package com.thiagosena.healthcheck.domain.models;

import com.thiagosena.healthcheck.domain.models.valueobjects.HealthCheckConfig;
import lombok.Builder;
import org.apache.commons.lang3.Validate;

import java.time.OffsetDateTime;
import java.util.UUID;

@Builder
public record HealthCheckTask(
        UUID id,
        App app,
        HealthCheckConfig checkConfig,
        OffsetDateTime createdAt,
        UUID scheduleId
) {
    public HealthCheckTask {
        Validate.notNull(id);
        Validate.notNull(app);
        Validate.notNull(checkConfig);
        Validate.notNull(createdAt);
        Validate.notNull(scheduleId);
    }
}