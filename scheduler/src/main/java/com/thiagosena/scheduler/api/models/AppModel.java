package com.thiagosena.scheduler.api.models;

import com.thiagosena.scheduler.domain.models.App;
import lombok.Builder;

import java.util.UUID;

@Builder
public record AppModel(UUID id, String name, String address) {
    public static AppModel of(App app) {
        return AppModel.builder()
                .id(app.getId())
                .name(app.getName())
                .address(app.getAddress())
                .build();
    }
}