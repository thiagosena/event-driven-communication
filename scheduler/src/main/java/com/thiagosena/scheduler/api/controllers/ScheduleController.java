package com.thiagosena.scheduler.api.controllers;

import com.thiagosena.scheduler.api.models.HealthCheckConfigModel;
import com.thiagosena.scheduler.api.models.ScheduleCreateRequest;
import com.thiagosena.scheduler.api.models.ScheduleModel;
import com.thiagosena.scheduler.api.models.ScheduleUpdateRequest;
import com.thiagosena.scheduler.domain.models.App;
import com.thiagosena.scheduler.domain.models.Schedule;
import com.thiagosena.scheduler.domain.models.valueobject.HealthCheckConfig;
import com.thiagosena.scheduler.domain.repositories.AppRepository;
import com.thiagosena.scheduler.domain.repositories.ScheduleRepository;
import com.thiagosena.scheduler.domain.services.ScheduleManagementService;
import com.thiagosena.scheduler.domain.services.exceptions.AppNotFoundException;
import com.thiagosena.scheduler.domain.services.exceptions.ScheduleNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/v1/schedules")
@RequiredArgsConstructor
public class ScheduleController {
    private final ScheduleManagementService scheduleManagementService;
    private final ScheduleRepository schedules;
    private final AppRepository apps;

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public ScheduleModel create(@RequestBody @Valid ScheduleCreateRequest request) {
        Schedule schedule = scheduleManagementService.create(toDomain(request));
        return ScheduleModel.of(schedule);
    }

    @PutMapping("/{scheduleId}")
    public ScheduleModel update(@PathVariable UUID scheduleId,
                                @RequestBody @Valid ScheduleUpdateRequest request) {
        Schedule schedule = scheduleManagementService.update(toDomain(scheduleId, request));
        return ScheduleModel.of(schedule);
    }

    @DeleteMapping("/{scheduleId}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void delete(@PathVariable UUID scheduleId) {
        scheduleManagementService.delete(scheduleId);
    }

    @GetMapping("/{scheduleId}")
    public ScheduleModel getById(@PathVariable UUID scheduleId) {
        Schedule schedule = schedules.findById(scheduleId).orElseThrow(ScheduleNotFoundException::new);
        return ScheduleModel.of(schedule);
    }

    @GetMapping
    public List<ScheduleModel> getAll() {
        return schedules.findAll().stream().map(ScheduleModel::of).toList();
    }

    private Schedule toDomain(ScheduleCreateRequest request) {
        HealthCheckConfigModel checkConfig = request.checkConfig();
        return Schedule.builder()
                .runInterval(request.runInterval())
                .app(findAppById(request.appId()))
                .checkConfig(HealthCheckConfig.builder()
                        .type(checkConfig.type())
                        .timeout(checkConfig.timeout())
                        .build())
                .build();
    }

    private Schedule toDomain(UUID scheduleId, ScheduleUpdateRequest request) {
        HealthCheckConfigModel checkConfig = request.checkConfig();
        return Schedule.builder()
                .id(scheduleId)
                .app(findAppById(request.appId()))
                .runInterval(request.runInterval())
                .checkConfig(HealthCheckConfig.builder()
                        .type(checkConfig.type())
                        .timeout(checkConfig.timeout())
                        .build())
                .build();
    }

    private App findAppById(UUID appId) {
        return apps.findById(appId).orElseThrow(AppNotFoundException::new);
    }
}