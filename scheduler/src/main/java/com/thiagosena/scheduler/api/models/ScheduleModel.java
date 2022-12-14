package com.thiagosena.scheduler.api.models;

import com.thiagosena.scheduler.domain.models.Schedule;
import lombok.Builder;

import java.util.UUID;

@Builder
public record ScheduleModel(
        UUID id,
        HealthCheckConfigModel checkConfig,
        Integer interval,
        AppModel app
) {
    public static ScheduleModel of(Schedule schedule) {
        return ScheduleModel.builder()
                .id(schedule.getId())
                .checkConfig(HealthCheckConfigModel.of(schedule.getCheckConfig()))
                .interval(schedule.getRunInterval())
                .app(AppModel.of(schedule.getApp()))
                .build();
    }
}