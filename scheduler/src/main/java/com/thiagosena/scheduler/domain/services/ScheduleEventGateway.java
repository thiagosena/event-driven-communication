package com.thiagosena.scheduler.domain.services;

import com.thiagosena.scheduler.domain.models.HealthCheckTask;

public interface ScheduleEventGateway {
    void sendExecuteTask(HealthCheckTask task);
}