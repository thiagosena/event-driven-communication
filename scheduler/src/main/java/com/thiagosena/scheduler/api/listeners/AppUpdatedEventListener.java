package com.thiagosena.scheduler.api.listeners;

import com.thiagosena.scheduler.api.models.AppModel;
import com.thiagosena.scheduler.domain.models.App;
import com.thiagosena.scheduler.domain.repositories.AppRepository;
import com.thiagosena.scheduler.domain.services.exceptions.AppNotFoundException;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import java.util.function.Consumer;

@Slf4j
@Component
@RequiredArgsConstructor
public class AppUpdatedEventListener implements Consumer<AppModel> {

    private final AppRepository apps;

    @Override
    public void accept(AppModel appModel) {
        log.info("Received updated app with id: " + appModel.id());
        App existingApp = apps.findById(appModel.id()).orElseThrow(AppNotFoundException::new);
        App appUpdated = App.builder()
                .id(appModel.id())
                .name(appModel.name())
                .address(appModel.address())
                .build();
        existingApp.update(appUpdated);
        apps.saveAndFlush(existingApp);
    }
}