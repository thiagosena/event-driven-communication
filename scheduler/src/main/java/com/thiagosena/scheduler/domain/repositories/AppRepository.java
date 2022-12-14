package com.thiagosena.scheduler.domain.repositories;

import com.thiagosena.scheduler.domain.models.App;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface AppRepository extends JpaRepository<App, UUID> {
}