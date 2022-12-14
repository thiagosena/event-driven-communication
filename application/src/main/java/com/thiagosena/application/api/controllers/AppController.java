package com.thiagosena.application.api.controllers;

import com.thiagosena.application.api.models.AppCreateRequest;
import com.thiagosena.application.api.models.AppModel;
import com.thiagosena.application.api.models.AppUpdateRequest;
import com.thiagosena.application.domain.models.App;
import com.thiagosena.application.domain.services.AppManagementService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import javax.transaction.Transactional;
import javax.validation.Valid;
import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/v1/apps")
@RequiredArgsConstructor
public class AppController {
    private final AppManagementService appManagementService;

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public AppModel create(@RequestBody @Valid AppCreateRequest request) {
        App app = appManagementService.create(App.builder()
                .name(request.name())
                .address(request.address())
                .build());
        return AppModel.of(app);
    }

    @PutMapping("/{appId}")
    public AppModel update(@PathVariable UUID appId,
                           @RequestBody @Valid AppUpdateRequest request) {
        App app = appManagementService.update(App.builder()
                .id(appId)
                .name(request.name())
                .address(request.address())
                .build());
        return AppModel.of(app);
    }

    @GetMapping("/{appId}")
    @Transactional
    public AppModel getById(@PathVariable UUID appId) {
        App app = appManagementService.findAppById(appId);
        return AppModel.of(app);
    }

    @GetMapping
    @Transactional
    public List<AppModel> getAll() {
        return appManagementService.findAll().stream().map(AppModel::of).toList();
    }
}