package com.tinoacuna.model;

import javax.persistence.*;
import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
@Entity
public class Role {

    @EqualsAndHashCode.Include
    @Id
    private Integer idRole;

    @Column(length = 10, nullable = false)
    private String name;

    @Column(nullable = false)
    private boolean enabled;
}
