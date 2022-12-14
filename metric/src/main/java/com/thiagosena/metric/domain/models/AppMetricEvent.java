package com.thiagosena.metric.domain.models;

import lombok.Builder;
import lombok.Getter;
import org.apache.commons.lang3.Validate;
import org.hibernate.annotations.GenericGenerator;
import org.hibernate.annotations.Type;

import javax.persistence.*;
import java.time.OffsetDateTime;
import java.util.UUID;

@Entity
@Getter
@Builder
@Table(name = "app_metric_event")
public class AppMetricEvent {
    @Id
    @GeneratedValue(generator = "uuid2")
    @GenericGenerator(name = "uuid2", strategy = "uuid2")
    @Column(name = "id", columnDefinition = "varchar(36)")
    @Type(type = "uuid-char")
    private UUID id;

    @Column(columnDefinition = "varchar(36)")
    @Type(type = "uuid-char")
    private UUID scheduleId;

    @Column(columnDefinition = "varchar(36)")
    @Type(type = "uuid-char")
    private UUID appId;

    private OffsetDateTime createdAt;

    private HealthCheckConfig.CheckType checkType;
    private HealthCheckTaskResult.Status resultStatus;

    protected AppMetricEvent() {
        //For JPA/Hibernate
    }

    public AppMetricEvent(UUID id, UUID scheduleId, UUID appId, OffsetDateTime createdAt,
                          HealthCheckConfig.CheckType checkType, HealthCheckTaskResult.Status resultStatus) {
        Validate.notNull(scheduleId);
        Validate.notNull(appId);
        Validate.notNull(createdAt);
        Validate.notNull(checkType);
        Validate.notNull(resultStatus);
        this.id = id;
        this.scheduleId = scheduleId;
        this.appId = appId;
        this.createdAt = createdAt;
        this.checkType = checkType;
        this.resultStatus = resultStatus;
    }

    public static AppMetricEvent of(HealthCheckTaskResult result) {
        Validate.notNull(result);
        return AppMetricEvent.builder()
                .checkType(result.checkConfig().type())
                .resultStatus(result.status())
                .scheduleId(result.scheduleId())
                .appId(result.appId())
                .createdAt(result.createdAt())
                .build();
    }

    public boolean isUp() {
        return HealthCheckTaskResult.Status.UP.equals(resultStatus);
    }

    public boolean isDown() {
        return HealthCheckTaskResult.Status.DOWN.equals(resultStatus);
    }
}