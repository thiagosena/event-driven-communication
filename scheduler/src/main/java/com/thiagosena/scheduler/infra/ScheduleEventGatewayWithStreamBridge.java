package com.thiagosena.scheduler.infra;

import com.thiagosena.scheduler.api.models.HealthCheckTaskRequest;
import com.thiagosena.scheduler.domain.models.HealthCheckTask;
import com.thiagosena.scheduler.domain.services.ScheduleEventGateway;
import lombok.RequiredArgsConstructor;
import org.springframework.cloud.stream.function.StreamBridge;
import org.springframework.stereotype.Component;

import static com.thiagosena.scheduler.config.AppProperties.HEALTH_CHECK_TASK_COMMAND_OUT_0;

@Component
@RequiredArgsConstructor
public class ScheduleEventGatewayWithStreamBridge implements ScheduleEventGateway {

    private final StreamBridge streamBridge;

    @Override
    public void sendExecuteTask(HealthCheckTask task) {
        HealthCheckTaskRequest request = HealthCheckTaskRequest.of(task);
        streamBridge.send(HEALTH_CHECK_TASK_COMMAND_OUT_0, request);
    }
}