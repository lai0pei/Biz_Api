package io.lab.core.modules.admin;


import io.lab.core.extension.AuditedTimeStamp;
import io.lab.core.modules.role.RoleMdl;
import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.UUID;

@Entity
@Table(name = "admin", indexes = { @Index(name= "idx_username", columnList = "username")})
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Builder
@NamedEntityGraph( attributeNodes = @NamedAttributeNode("role"))
public class AdminMdl implements Serializable  {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;

    @Column( unique = true, nullable = false, length = 255)
    private String username;

    @Column(nullable = false, length = 255)
    private String password;

    @Column(nullable = false, length = 255)
    private String nickname;

    @Column(columnDefinition = "BOOLEAN DEFAULT TRUE")
    @Builder.Default
    private Boolean enabled = true;

    @Column(columnDefinition = "BOOLEAN DEFAULT FALSE")
    @Builder.Default
    private Boolean superAdmin = false;

    @Builder.Default
    @Column
    private LocalDateTime lastLoginTime = null;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(referencedColumnName = "id")
    private RoleMdl role;

    @CreationTimestamp
    @Builder.Default
    @Column(columnDefinition = "TIMESTAMP DEFAULT CURRENT_TIMESTAMP")
    private LocalDateTime createdTime = null;

    @UpdateTimestamp
    @Builder.Default
    @Column(columnDefinition = "TIMESTAMP DEFAULT CURRENT_TIMESTAMP")
    private LocalDateTime updatedTime = null;

}
