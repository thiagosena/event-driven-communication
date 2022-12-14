package com.thiagosena.application.domain.models;

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
@Table(name = "app")
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
public class App {
    @Id
    @GeneratedValue(generator = "uuid2")
    @GenericGenerator(name = "uuid2", strategy = "uuid2")
    @EqualsAndHashCode.Include
    @Column(name = "id", nullable = false, updatable = false, columnDefinition = "varchar(36)")
    @Type(type = "uuid-char")
    private UUID id;
    private String name;
    private String address;

    protected App() {
        // For JPA/Hibernate
    }

    public App(UUID id, String name, String address) {
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