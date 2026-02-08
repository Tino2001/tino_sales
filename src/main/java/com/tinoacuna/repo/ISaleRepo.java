package com.tinoacuna.repo;

import com.tinoacuna.dto.IProcedureDTO;
import com.tinoacuna.dto.ProcedureDTO;
import com.tinoacuna.model.Sale;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.jpa.repository.query.Procedure;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface ISaleRepo extends IGenericRepo<Sale, Integer> {

    //[4,	"28/09/2022"]
    @Query(value = "select * from fn_sales()", nativeQuery = true)
    List<Object[]> callProcedure();

    @Query(nativeQuery = true, name = "Sale.fn_sales")
    List<ProcedureDTO> callProcedure2();

    @Procedure(procedureName = "fn_sales")
    List<IProcedureDTO> callProcedure3();

    @Procedure(procedureName = "fn_salesParameter" )
    List<IProcedureDTO> callProcedure4(@Param("p_id_client") Integer idClient);
}
