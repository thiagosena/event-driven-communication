package com.thiagosena.metric.domain.services;

import com.thiagosena.metric.domain.models.AppMetric;
import com.thiagosena.metric.domain.models.AppMetricEvent;
import com.thiagosena.metric.domain.models.HealthCheckTaskResult;
import com.thiagosena.metric.domain.repositories.AppMetricEventRepository;
import com.thiagosena.metric.domain.repositories.AppMetricRepository;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.Validate;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
@Slf4j
public class MetricService {

    private final AppMetricRepository metrics;
    private final AppMetricEventRepository events;

    public void process(HealthCheckTaskResult actualResult) {
        Validate.notNull(actualResult);
        log.info("Updating task metrics " + actualResult.taskId());

        AppMetricEvent actualMetricEvent = events.saveAndFlush(AppMetricEvent.of(actualResult));

        var possibleLastMetricEvent =
                events.findFirstByCreatedAtBeforeAndCheckTypeAndIdNotOrderByCreatedAtDesc(actualResult.createdAt(),
                        actualResult.checkConfig().type(),
                        actualResult.id());

        AppMetric metric = metrics.findOneByAppId(actualResult.appId()).orElseGet(() -> AppMetric.of(actualResult));

        possibleLastMetricEvent.ifPresent(latMetricEvent -> metric.processResult(latMetricEvent, actualMetricEvent));

        metrics.saveAndFlush(metric);
    }

}