package com.tinoacuna.model;

import javax.persistence.*;
import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
@IdClass(IngressDetailPK.class)
public class IngressDetail {

    @Id
    private Ingress ingress;

    @Id
    private Product product;

    @Column(nullable = false)
    private short quantity;

    @Column(columnDefinition = "decimal(6,2)",nullable = false)
    private short cost;

}
