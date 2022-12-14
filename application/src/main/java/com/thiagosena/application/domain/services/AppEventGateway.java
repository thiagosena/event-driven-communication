package com.thiagosena.application.domain.services;

import com.thiagosena.application.domain.models.App;

public interface AppEventGateway {
    void sendAppCreatedEvent(App app);

    void sendAppUpdatedEvent(App app);
}