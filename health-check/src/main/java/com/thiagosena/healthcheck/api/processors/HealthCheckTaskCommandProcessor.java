package com.thiagosena.healthcheck.api.processors;

import com.thiagosena.healthcheck.api.models.HealthCheckTaskRequest;
import com.thiagosena.healthcheck.api.models.HealthCheckTaskResultModel;
import com.thiagosena.healthcheck.domain.models.HealthCheckTask;
import com.thiagosena.healthcheck.domain.services.HealthCheckService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.function.Function;

@Component
@RequiredArgsConstructor
public class HealthCheckTaskCommandProcessor implements Function<HealthCheckTaskRequest, HealthCheckTaskResultModel> {

    private final HealthCheckService healthCheckService;

    @Override
    public HealthCheckTaskResultModel apply(HealthCheckTaskRequest request) {
        HealthCheckTask task = request.toDomain();
        return HealthCheckTaskResultModel.of(healthCheckService.checkIfIsUp(task));
    }
}