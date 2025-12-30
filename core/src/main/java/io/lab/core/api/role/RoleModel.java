package io.lab.core.api.role;

import io.lab.core.api.admin.AdminModel;
import io.lab.core.api.model.AuthorityModel;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.Set;


@Entity
@Table(name = "role")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class RoleModel {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Column(name = "name")
    private String name;

    @Column(name = "created_time", nullable = false)
    private LocalDateTime createdTime;

    @Column(name = "updated_time")
    private LocalDateTime updatedTime;

    @Transient
    @OneToMany(mappedBy = "role", fetch = FetchType.LAZY)
    private Set<AdminModel> adminModels = new HashSet<>();

    @Transient
    @OneToMany(mappedBy = "role", fetch = FetchType.LAZY)
    private Set<AuthorityModel> authorities = new HashSet<>();

}
