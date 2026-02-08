package com.tinoacuna.model;

import javax.persistence.*;
import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "user_data")
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
public class User {

    @Id
    @EqualsAndHashCode.Include
    private Integer idUser;

    @ManyToOne //FK
    @JoinColumn(name = "id_role", nullable = false, foreignKey = @ForeignKey(name = "FK_User_Role"))
    private Role role;

    @Column(length = 50, nullable = false)
    private String username;

    @Column(length = 60, nullable = false)
    private String password;

    @Column(nullable = false)
    private boolean enabled;
}
