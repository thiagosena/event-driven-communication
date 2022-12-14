package com.thiagosena.healthcheck.domain.services;

import com.thiagosena.healthcheck.domain.models.App;
import com.thiagosena.healthcheck.domain.models.HealthCheckTask;
import com.thiagosena.healthcheck.domain.models.HealthCheckTaskResult;
import com.thiagosena.healthcheck.domain.models.valueobjects.HealthCheckType;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.Validate;
import org.springframework.stereotype.Service;

@Service
@Slf4j
public class HealthCheckService {
    public HealthCheckTaskResult checkIfIsUp(HealthCheckTask task) {
        Validate.notNull(task);
        App app = task.app();
        log.info("Checking app: " + app.address());

        HealthCheckType type = task.checkConfig().type();
        HealthResult result = type.getHealthChecker().execute(task);

        return HealthCheckTaskResult.of(task, result);
    }
}