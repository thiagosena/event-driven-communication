package com.thiagosena.healthcheck.infra;

import com.thiagosena.healthcheck.domain.models.HealthCheckTask;
import com.thiagosena.healthcheck.domain.models.HealthCheckTaskResult;
import com.thiagosena.healthcheck.domain.services.HealthChecker;
import com.thiagosena.healthcheck.domain.services.HealthResult;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.Validate;

import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.time.Duration;

@Slf4j
public class HttpHealthChecker implements HealthChecker {

    private final HttpClient client = HttpClient.newHttpClient();

    @Override
    public HealthResult execute(HealthCheckTask task) {
        Validate.notNull(task);
        Validate.notNull(task.app());
        Validate.notNull(task.checkConfig());

        var checkConfig = task.checkConfig();
        var app = task.app();

        try {
            if (isReachable(app.address(), checkConfig.timeout())) {
                log.info(String.format("Verification via HTTP detected %s address as online", app.address()));
                return getOnlineResponse();
            } else {
                log.info(String.format("Verification via HTTP detected %s address as offline", app.address()));
                return getOfflineResponse();
            }
        } catch (Exception e) {
            log.error(String.format("Failed to query the %s address via HTTP", app.address()), e);
            return getUnknownResponse();
        }

    }

    private boolean isReachable(String address, Integer timeoutInSeconds) throws Exception {
        HttpRequest request = HttpRequest.newBuilder()
                .uri(URI.create(address))
                .timeout(Duration.ofSeconds(timeoutInSeconds))
                .build();

        HttpResponse<String> httpResponse = client.send(request, HttpResponse.BodyHandlers.ofString());
        return httpResponse.statusCode() >= 200 && httpResponse.statusCode() <= 204;
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