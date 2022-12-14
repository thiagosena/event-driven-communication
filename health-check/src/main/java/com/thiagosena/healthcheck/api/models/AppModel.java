package com.thiagosena.healthcheck.api.models;

import java.util.UUID;

public record AppModel(UUID id, String name, String address) {
}