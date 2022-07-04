package com.crud.springbootcrud.model;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.Type;
import org.hibernate.annotations.UpdateTimestamp;

import javax.persistence.*;
import java.time.Instant;
import java.util.UUID;

@ToString
@Getter
@Setter
@MappedSuperclass
public abstract class BaseEntity {

    @Id @Type(type = "pg-uuid")
    private UUID id;

    public BaseEntity() {
        this.id = UUID.randomUUID();
    }

    @CreationTimestamp
    @Column(name = "created_at", updatable = false, nullable = false)
    protected Instant createdAt;

    @UpdateTimestamp
    @Column(name = "modified_at")
    protected Instant modifiedAt;

    @Column
    @Version
    protected int version;

}
