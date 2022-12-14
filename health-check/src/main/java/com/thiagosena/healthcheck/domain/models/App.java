package com.thiagosena.healthcheck.domain.models;

import lombok.Builder;
import org.apache.commons.lang3.Validate;

import java.util.UUID;

@Builder
public record App(UUID id, String name, String address) {
    public App {
        Validate.notNull(id);
        Validate.notNull(name);
        Validate.notNull(address);
    }
}