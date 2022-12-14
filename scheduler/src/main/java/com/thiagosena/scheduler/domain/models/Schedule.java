package com.thiagosena.scheduler.domain.models;

import com.thiagosena.scheduler.domain.models.valueobject.HealthCheckConfig;
import lombok.Builder;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import org.apache.commons.lang3.Validate;
import org.hibernate.annotations.GenericGenerator;
import org.hibernate.annotations.Type;

import javax.persistence.*;
import java.util.UUID;

@Entity
@Getter
@Builder
@Table(name = "schedule")
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
public class Schedule {
    @Id
    @GeneratedValue(generator = "uuid2")
    @GenericGenerator(name = "uuid2", strategy = "uuid2")
    @EqualsAndHashCode.Include
    @Column(name = "id", nullable = false, updatable = false, columnDefinition = "varchar(36)")
    @Type(type = "uuid-char")
    private UUID id;

    @Embedded
    private HealthCheckConfig checkConfig;

    private Integer runInterval;

    @ManyToOne(optional = false)
    @JoinColumn(name = "app_id", columnDefinition = "varchar(36)")
    private App app;

    protected Schedule() {
        //For JPA/Hibernate
    }

    public Schedule(UUID id, HealthCheckConfig checkConfig, Integer runInterval, App app) {
        Validate.notNull(checkConfig);
        Validate.notNull(runInterval);
        Validate.notNull(app);
        this.id = id;
        this.checkConfig = checkConfig;
        this.runInterval = runInterval;
        this.app = app;
    }

    public void update(Schedule schedule) {
        Validate.notNull(schedule);
        this.checkConfig = schedule.getCheckConfig();
        this.runInterval = schedule.getRunInterval();
    }
}