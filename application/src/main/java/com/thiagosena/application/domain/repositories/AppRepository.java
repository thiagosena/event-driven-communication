package com.thiagosena.application.domain.repositories;

import com.thiagosena.application.domain.models.App;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface AppRepository extends JpaRepository<App, UUID> {
}