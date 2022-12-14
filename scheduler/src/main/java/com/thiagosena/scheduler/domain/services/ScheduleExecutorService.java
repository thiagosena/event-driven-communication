package com.thiagosena.scheduler.domain.services;

import com.thiagosena.scheduler.domain.models.HealthCheckTask;
import com.thiagosena.scheduler.domain.repositories.ScheduleRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

@Slf4j
@Service
@RequiredArgsConstructor
public class ScheduleExecutorService {
    private final ScheduleRepository schedules;
    private final ScheduleEventGateway scheduleEventGateway;

    @Scheduled(cron = "0 */1 * * * *")
    public void doExecuteEachOneMinuteSchedule() {
        log.info("Executando check de um minuto");
        this.schedules.findAllByRunInterval(1).forEach(schedule -> {
            HealthCheckTask task = HealthCheckTask.of(schedule);
            scheduleEventGateway.sendExecuteTask(task);
        });
    }

    @Scheduled(cron = "0 */5 * * * *")
    public void doExecuteEachFiveMinutesSchedule() {
        log.info("Executando check de cinco minutos");
        this.schedules.findAllByRunInterval(5).forEach(schedule -> {
            HealthCheckTask task = HealthCheckTask.of(schedule);
            scheduleEventGateway.sendExecuteTask(task);
        });
    }

    @Scheduled(cron = "0 */15 * * * *")
    public void doExecuteEachFifteenMinuteSchedule() {
        log.info("Executando check de quinze minutos");
        this.schedules.findAllByRunInterval(15).forEach(schedule -> {
            HealthCheckTask task = HealthCheckTask.of(schedule);
            scheduleEventGateway.sendExecuteTask(task);
        });
    }

    @Scheduled(cron = "0 */30 * * * *")
    public void doExecuteEachThirteenMinuteSchedule() {
        log.info("Executando check de trinta minutos");
        this.schedules.findAllByRunInterval(30).forEach(schedule -> {
            HealthCheckTask task = HealthCheckTask.of(schedule);
            scheduleEventGateway.sendExecuteTask(task);
        });
    }

    @Scheduled(cron = "0 0 */1 * * *")
    public void doExecuteEachSixteenMinuteSchedule() {
        log.info("Executando check de sessenta minutos");
        this.schedules.findAllByRunInterval(60).forEach(schedule -> {
            HealthCheckTask task = HealthCheckTask.of(schedule);
            scheduleEventGateway.sendExecuteTask(task);
        });
    }
}