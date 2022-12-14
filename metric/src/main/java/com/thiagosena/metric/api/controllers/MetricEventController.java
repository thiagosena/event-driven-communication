package com.thiagosena.metric.api.controllers;

import com.thiagosena.metric.api.models.AppMetricEventModel;
import com.thiagosena.metric.domain.repositories.AppMetricEventRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/v1/metrics/events")
@RequiredArgsConstructor
public class MetricEventController {
    private final AppMetricEventRepository results;

    @GetMapping
    public List<AppMetricEventModel> findAll() {
        return results.findAll()
                .stream()
                .map(AppMetricEventModel::of)
                .toList();
    }
}