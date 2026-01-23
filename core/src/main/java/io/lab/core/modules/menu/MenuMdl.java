package io.lab.core.modules.menu;

import io.lab.core.modules.permission.PermMdl;
import io.lab.core.modules.permission.PermMdl_;
import jakarta.persistence.*;
import lombok.*;

import java.util.List;
import java.util.Set;

@Entity
@Table(name = "menu")
@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class MenuMdl {
    //non auto increment
    @Id
    private Long id;

    @Enumerated(EnumType.STRING)
    @Column(unique = true, length = 255)
    private MenuSig menuSig;

    @Enumerated(EnumType.STRING)
    @Column(length = 255)
    private MenuRank menuRank;

    @Column
    private Long parentId;

    @Column
    @Builder.Default
    private Integer sort = 0;

    @OneToMany( mappedBy = PermMdl_.MENU , fetch = FetchType.LAZY)
    private List<PermMdl> permMdl;
}
