package com.tinoacuna.model;

import javax.persistence.*;
import lombok.*;

import java.io.Serializable;

@EqualsAndHashCode
@Embeddable
public class IngressDetailPK implements Serializable {

    @ManyToOne
    @JoinColumn(name = "id_ingress", nullable = false)
    private Ingress ingress;

    @ManyToOne
    @JoinColumn(name = "id_product", nullable = false)
    private Product product;

}
