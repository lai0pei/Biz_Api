package io.lab.core.modules.role;

import io.lab.core.extension.AuditedTimeStamp;
import io.lab.core.modules.permission.PermMdl;
import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Set;


@Entity
@Table(name = "role")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class RoleMdl {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column
    private String roleName;

    @Column
    @Builder.Default
    private Boolean enabled = true;

    @ManyToMany
    @JoinTable(
            name="permission_grant",
            joinColumns = @JoinColumn(name="role_id"),
            inverseJoinColumns = @JoinColumn(name="perm_id"))
    List<PermMdl> permMdl;

    @CreationTimestamp
    @Builder.Default
    @Column(columnDefinition = "TIMESTAMP DEFAULT CURRENT_TIMESTAMP")
    private LocalDateTime createdTime = null;

    @UpdateTimestamp
    @Builder.Default
    @Column(columnDefinition = "TIMESTAMP DEFAULT CURRENT_TIMESTAMP")
    private LocalDateTime updatedTime = null;

}
