package com.thiagosena.scheduler.api.models;

import com.thiagosena.scheduler.api.validators.ValidInterval;
import org.jetbrains.annotations.NotNull;

import javax.validation.Valid;
import java.util.UUID;

public record ScheduleUpdateRequest(
        @ValidInterval
        Integer runInterval,
        @NotNull
        @Valid
        HealthCheckConfigModel checkConfig,
        @NotNull
        UUID appId
) {
}