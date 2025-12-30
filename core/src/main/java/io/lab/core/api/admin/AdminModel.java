package io.lab.core.api.admin;


import io.lab.core.api.role.RoleModel;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

import java.math.BigInteger;
import java.time.LocalDateTime;

@Entity
@Table(name = "admin")
@NoArgsConstructor
@Getter
@Setter
public class AdminModel {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Column( unique = true, nullable = false)
    private String username;

    @Column(nullable = false)
    private String password;

    @Column(nullable = false)
    private String name;

    @Column(nullable = false)
    private long roleId;

    @Column
    private boolean isEnabled = true;

    @Column
    private boolean isSuperAdmin = false;

    @Column
    private LocalDateTime lastLoginTime;

    @Column( nullable = false)
    private LocalDateTime createdTime;

    @Column
    private LocalDateTime updatedTime;

    @Transient
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "role_id")
    private RoleModel role;

    @PreUpdate
    public void preUpdate() {
        this.updatedTime = LocalDateTime.now();
    }

    @PrePersist
    public void prePersist() {
        this.createdTime = LocalDateTime.now();
        this.updatedTime = LocalDateTime.now();
    }

}
