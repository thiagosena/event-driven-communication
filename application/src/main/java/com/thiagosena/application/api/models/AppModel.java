package com.thiagosena.application.api.models;

import com.thiagosena.application.domain.models.App;
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