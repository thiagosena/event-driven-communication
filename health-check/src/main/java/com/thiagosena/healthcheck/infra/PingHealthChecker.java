package com.thiagosena.healthcheck.infra;

import com.thiagosena.healthcheck.domain.models.HealthCheckTask;
import com.thiagosena.healthcheck.domain.models.HealthCheckTaskResult;
import com.thiagosena.healthcheck.domain.services.HealthChecker;
import com.thiagosena.healthcheck.domain.services.HealthResult;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.Validate;

import java.io.IOException;
import java.net.InetAddress;

@Slf4j
public class PingHealthChecker implements HealthChecker {
    @Override
    public HealthResult execute(HealthCheckTask task) {
        Validate.notNull(task);
        Validate.notNull(task.app());
        Validate.notNull(task.checkConfig());

        var checkConfig = task.checkConfig();
        var app = task.app();

        //timer
        try {
            if (isReachable(app.address(), checkConfig.timeout())) {
                log.info(String.format("Verification via PING detected %s address as online", app.address()));
                return getOnlineResponse();
            } else {
                log.info(String.format("Verification via PING detected %s address as offline", app.address()));
                return getOfflineResponse();
            }
        } catch (Exception e) {
            log.error(String.format("Failed to query the %s address via PING", app.address()), e);
            return getUnknownResponse();
        }

    }

    private boolean isReachable(String address, Integer timeout) throws IOException {
        return InetAddress.getByName(address).isReachable(timeout);
    }

    private HealthResult getOnlineResponse() {
        return HealthResult.builder()
                .responseTime(10)
                .status(HealthCheckTaskResult.Status.UP)
                .build();
    }

    private HealthResult getOfflineResponse() {
        return HealthResult.builder()
                .responseTime(10)
                .status(HealthCheckTaskResult.Status.DOWN)
                .build();
    }

    private static HealthResult getUnknownResponse() {
        return HealthResult.builder()
                .responseTime(10)
                .status(HealthCheckTaskResult.Status.UNKNOWN)
                .build();
    }
}