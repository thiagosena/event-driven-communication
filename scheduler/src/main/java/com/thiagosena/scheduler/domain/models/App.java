package com.thiagosena.scheduler.domain.models;

import lombok.Builder;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import org.apache.commons.lang3.Validate;
import org.hibernate.annotations.Type;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.util.UUID;

@Entity
@Getter
@Builder
@Table(name = "app")
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
public class App {
    @Id
    @EqualsAndHashCode.Include
    @Column(name = "id", columnDefinition = "varchar(36)")
    @Type(type = "uuid-char")
    private UUID id;
    private String name;
    private String address;

    protected App() {
        //For JPA/Hibernate
    }

    public App(UUID id, String name, String address) {
        Validate.notNull(id);
        Validate.notNull(name);
        Validate.notNull(address);
        this.id = id;
        this.name = name;
        this.address = address;
    }

    public void update(App app) {
        Validate.notNull(app);
        this.name = app.getName();
        this.address = app.getAddress();
    }
}