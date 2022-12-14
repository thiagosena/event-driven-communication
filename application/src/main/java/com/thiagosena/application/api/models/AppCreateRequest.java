package com.thiagosena.application.api.models;

import javax.validation.constraints.NotBlank;

public record AppCreateRequest(
        @NotBlank
        String name,
        @NotBlank
        String address
) {
}