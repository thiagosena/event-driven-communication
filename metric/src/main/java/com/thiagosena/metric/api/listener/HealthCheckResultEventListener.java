package com.thiagosena.metric.api.listener;

import com.thiagosena.metric.api.models.HealthCheckTaskResultModel;
import com.thiagosena.metric.domain.models.HealthCheckTaskResult;
import com.thiagosena.metric.domain.services.MetricService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.function.Consumer;

@Component
@RequiredArgsConstructor
public class HealthCheckResultEventListener implements Consumer<HealthCheckTaskResultModel> {

    private final MetricService metricService;

    @Override
    public void accept(HealthCheckTaskResultModel model) {
        HealthCheckTaskResult taskResult = model.toDomain();
        metricService.process(taskResult);
    }
}