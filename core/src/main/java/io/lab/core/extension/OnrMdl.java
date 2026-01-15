package io.lab.core.extension;

import jakarta.persistence.Column;
import jakarta.persistence.MappedSuperclass;

import java.util.UUID;

@MappedSuperclass
public abstract class OnrMdl {
    @Column(nullable = true)
    UUID ownerId;
}
