package com.thiagosena.application.infra;

import com.thiagosena.application.api.models.AppModel;
import com.thiagosena.application.domain.models.App;
import com.thiagosena.application.domain.services.AppEventGateway;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.cloud.stream.function.StreamBridge;
import org.springframework.stereotype.Component;

import static com.thiagosena.application.config.AppProperties.APP_CREATED_SUPPLIER_OUT_0;
import static com.thiagosena.application.config.AppProperties.APP_UPDATED_SUPPLIER_OUT_0;

@Slf4j
@Component
@RequiredArgsConstructor
public class AppEventGatewayWithStreamBridge implements AppEventGateway {
    private final StreamBridge streamBridge;

    @Override
    public void sendAppCreatedEvent(App app) {
        log.info(String.format("App with id %s created", app.getId()));
        streamBridge.send(APP_CREATED_SUPPLIER_OUT_0, AppModel.of(app));
    }

    @Override
    public void sendAppUpdatedEvent(App app) {
        log.info(String.format("App with id %s updated", app.getId()));
        streamBridge.send(APP_UPDATED_SUPPLIER_OUT_0, AppModel.of(app));
    }
}