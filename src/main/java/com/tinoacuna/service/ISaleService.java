package com.tinoacuna.service;

import com.tinoacuna.dto.IProcedureDTO;
import com.tinoacuna.dto.ProcedureDTO;
import com.tinoacuna.model.Sale;

import java.util.List;

public interface ISaleService extends ICRUD<Sale, Integer>{

    List<ProcedureDTO> callProcedure();
    List<ProcedureDTO> callProcedure2();
    List<IProcedureDTO> callProcedure3();
    List<IProcedureDTO> callProcedure4(Integer idClient);


}
