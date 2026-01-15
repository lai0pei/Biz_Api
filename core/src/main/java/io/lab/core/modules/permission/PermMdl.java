package io.lab.core.modules.permission;

import io.lab.core.modules.menu.MenuMdl;
import jakarta.persistence.*;
import lombok.*;

import java.security.Permission;

@Entity
@Table(name = "permission", indexes = { @Index(name= "idx_permission_type", columnList = "permission_type")})
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class PermMdl {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false, unique = true)
    private PermType permission_type;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(referencedColumnName = "id")
    private MenuMdl menu;
}