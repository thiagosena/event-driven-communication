package com.thiagosena.metric.api.controllers;

import com.thiagosena.metric.api.models.AppMetricModel;
import com.thiagosena.metric.domain.repositories.AppMetricRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/v1/metrics")
@RequiredArgsConstructor
public class MetricController {
    private final AppMetricRepository metrics;

    @GetMapping
    public List<AppMetricModel> findAll() {
        return metrics.findAll()
                .stream()
                .map(AppMetricModel::of)
                .toList();
    }
}