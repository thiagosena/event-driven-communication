package com.thiagosena.scheduler.api.listeners;

import com.thiagosena.scheduler.api.models.AppModel;
import com.thiagosena.scheduler.domain.models.App;
import com.thiagosena.scheduler.domain.repositories.AppRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import java.util.function.Consumer;

@Slf4j
@Component
@RequiredArgsConstructor
public class AppCreatedEventListener implements Consumer<AppModel> {

    private final AppRepository apps;

    @Override
    public void accept(AppModel appModel) {
        log.info("Received created app with id: " + appModel.id());
        App app = App.builder()
                .id(appModel.id())
                .name(appModel.name())
                .address(appModel.address())
                .build();
        apps.saveAndFlush(app);
    }
}