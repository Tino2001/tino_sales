package com.tinoacuna.service.impl;

import com.tinoacuna.dto.IProcedureDTO;
import com.tinoacuna.dto.ProcedureDTO;
import com.tinoacuna.model.Sale;
import com.tinoacuna.repo.IGenericRepo;
import com.tinoacuna.repo.ISaleRepo;
import com.tinoacuna.service.ISaleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

@Service
public class SaleServiceImpl extends CRUDImpl<Sale, Integer> implements ISaleService {

    @Autowired
    private ISaleRepo repo;

    @Override
    protected IGenericRepo<Sale, Integer> getRepo() {
        return repo;
    }

    @Override
    public List<ProcedureDTO> callProcedure() {
        //List<Object[]>
        //[4,	"28/09/2022"]
        List<ProcedureDTO> lst = new ArrayList<>();
        repo.callProcedure().forEach(e -> {
            ProcedureDTO dto = new ProcedureDTO();
            dto.setQuantityfn((Integer) e[0]);
            dto.setDatetimefn((String) e[1]);
            lst.add(dto);
        });
        return lst;
    }

    @Override
    public List<ProcedureDTO> callProcedure2() {
        return repo.callProcedure2();
    }

    @Transactional
    @Override
    public List<IProcedureDTO> callProcedure3() {
        return repo.callProcedure3();
    }

    @Transactional
    @Override
    public List<IProcedureDTO> callProcedure4(Integer idClient){
        return repo.callProcedure4(idClient);
    }
}
