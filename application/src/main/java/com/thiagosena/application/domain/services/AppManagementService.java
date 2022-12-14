package com.thiagosena.application.domain.services;

import com.thiagosena.application.domain.models.App;
import com.thiagosena.application.domain.repositories.AppRepository;
import com.thiagosena.application.domain.services.exceptions.AppNotFoundException;
import lombok.RequiredArgsConstructor;
import org.apache.commons.lang3.Validate;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class AppManagementService {
    private final AppRepository apps;
    private final AppEventGateway appEventGateway;

    @Transactional
    public App create(App app) {
        Validate.notNull(app);
        apps.saveAndFlush(app);
        appEventGateway.sendAppCreatedEvent(app);

        return app;
    }

    @Transactional
    public App update(App updatedApp) {
        Validate.notNull(updatedApp);

        App existingApp = findAppById(updatedApp.getId());

        existingApp.update(updatedApp);
        apps.saveAndFlush(existingApp);
        appEventGateway.sendAppUpdatedEvent(existingApp);

        return existingApp;
    }

    public App findAppById(UUID appId) {
        return apps.findById(appId).orElseThrow(AppNotFoundException::new);
    }

    public List<App> findAll() {
        return apps.findAll();
    }
}