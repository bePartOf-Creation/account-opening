package com.accountopeningapplication.entities;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import de.huxhorn.sulky.ulid.ULID;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.sql.Timestamp;
import java.util.Calendar;

@Getter
@Setter
@MappedSuperclass
@AllArgsConstructor
@NoArgsConstructor
@JsonIgnoreProperties(ignoreUnknown = true)
public class BaseEntity {
    @Id
    @Column(name = "id", unique = true, nullable = false, updatable = false, length = 26)
    private String id;

    @Column(nullable = false, updatable = false)
    private Timestamp createdOn;

    @Column(name = "updated_on")
    private Timestamp updatedOn;

    @PrePersist
    public void prePersist() {
        this.id = new ULID().nextValue().toString();
        createdOn = Timestamp.from(Calendar.getInstance().toInstant());
        updatedOn = Timestamp.from(Calendar.getInstance().toInstant());
    }

    @PreUpdate
    public void preUpdate() {
        updatedOn = Timestamp.from(Calendar.getInstance().toInstant());
    }
}
