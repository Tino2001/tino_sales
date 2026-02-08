package com.tinoacuna.model;

import com.tinoacuna.dto.IProcedureDTO;
import com.tinoacuna.dto.ProcedureDTO;
import javax.persistence.*;
import lombok.*;

import java.time.LocalDateTime;
import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
@EqualsAndHashCode(onlyExplicitlyIncluded = true)

@SqlResultSetMapping(
        name = "Procedure.ProcedureDTO",
        classes = @ConstructorResult(targetClass = ProcedureDTO.class,
                columns = {@ColumnResult(name = "quantityfn", type = Integer.class),
                        @ColumnResult(name = "datetimefn", type = String.class)}
        )
)
@NamedNativeQuery(
        name = "Sale.fn_sales",
        query = "select * from fn_sales()",
        resultSetMapping = "Procedure.ProcedureDTO"
)

////////////////////////////////////////////////////////
@NamedStoredProcedureQuery(
        name = "getFnSales",
        procedureName = "fn_sales",
        resultClasses = IProcedureDTO.class
        )

@NamedStoredProcedureQuery(
        name = "getFnSales2", //un alias a la configuracion
        procedureName = "fn_salesParameter", //debe coincidir con lo definido en Repo
        resultClasses = IProcedureDTO.class,
        parameters = {
                @StoredProcedureParameter(name = "p_id_client", type = Integer.class, mode = ParameterMode.IN) //,
                //@StoredProcedureParameter(name = "ABC", type = void.class, mode = ParameterMode.REF_CURSOR),
                //@StoredProcedureParameter(name = "DEF", type = String.class, mode = ParameterMode.OUT),
        })
public class Sale {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @EqualsAndHashCode.Include
    private Integer idSale;

    @ManyToOne
    @JoinColumn(name = "id_client", nullable = false)
    private Client client;

    @ManyToOne
    @JoinColumn(name = "id_user", nullable = false)
    private User user;

    @Column(nullable = false)
    private LocalDateTime datetime;

    @Column(columnDefinition = "decimal(6,2)", nullable = false)
    private double total;

    @Column(columnDefinition = "decimal(6,2)", nullable = false)
    private double tax;

    @OneToMany(mappedBy = "sale", cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    private List<SaleDetail> details;
}
