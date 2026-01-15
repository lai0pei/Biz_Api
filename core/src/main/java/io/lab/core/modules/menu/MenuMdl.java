package io.lab.core.modules.menu;

import jakarta.persistence.*;
import lombok.*;

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
}
