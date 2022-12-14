package com.thiagosena.metric.api.models;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.thiagosena.metric.domain.models.AppMetric;
import lombok.Builder;
import org.apache.commons.lang3.StringUtils;

import java.text.NumberFormat;
import java.time.Duration;
import java.util.UUID;

@Builder
public record AppMetricModel(
        UUID id,
        String upTime,
        String downTime,
        UUID appId
) {
    public static AppMetricModel of(AppMetric metric) {
        return AppMetricModel.builder()
                .id(metric.getId())
                .upTime(metric.getUpTime())
                .downTime(metric.getDownTime())
                .appId(metric.getAppId())
                .build();
    }

    @JsonProperty
    public String getUpTimeDescription() {
        if (StringUtils.isBlank(this.upTime)) {
            return "";
        }

        if (StringUtils.isBlank(this.downTime)) {
            return "";
        }

        Duration upDays = Duration.parse(this.upTime);
        Duration downDays = Duration.parse(this.downTime);
        Duration totalDays = upDays.plus(downDays);

        return getDuration(upDays, totalDays);

    }

    @JsonProperty
    public String getDownTimeDescription() {
        if (StringUtils.isBlank(this.downTime)) {
            return "";
        }

        if (StringUtils.isBlank(this.upTime)) {
            return "";
        }

        Duration upDays = Duration.parse(this.upTime);
        Duration downDays = Duration.parse(this.downTime);
        Duration totalDays = upDays.plus(downDays);

        return getDuration(downDays, totalDays);

    }

    private String getDuration(Duration durationDays, Duration totalDays) {
        try {
            float durationMinutes = durationDays.toMinutes();
            float totalMinutes = totalDays.toMinutes();

            if (totalMinutes == 0) {
                return "";
            }

            return NumberFormat.getPercentInstance().format(durationMinutes / totalMinutes);
        } catch (ArithmeticException e) {
            return "";
        }
    }
}