package com.thiagosena.scheduler.domain.models;

import com.thiagosena.scheduler.domain.models.valueobject.HealthCheckConfig;
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

    public static HealthCheckTask of(Schedule schedule) {
        Validate.notNull(schedule);
        Validate.notNull(schedule.getCheckConfig());
        return HealthCheckTask.builder()
                .id(UUID.randomUUID())
                .scheduleId(schedule.getId())
                .app(schedule.getApp())
                .checkConfig(schedule.getCheckConfig())
                .createdAt(OffsetDateTime.now())
                .build();
    }
}