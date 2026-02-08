package com.tinoacuna.controller;

import com.tinoacuna.dto.IProcedureDTO;
import com.tinoacuna.dto.ProcedureDTO;
import com.tinoacuna.dto.SaleDTO;
import com.tinoacuna.exception.ModelNotFoundException;
import com.tinoacuna.model.Sale;
import com.tinoacuna.service.ISaleService;
import javax.validation.Valid;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/sales")
public class SaleController {

    @Autowired
    private ISaleService service;

    @Autowired
    @Qualifier("saleMapper")
    private ModelMapper mapper;

    @GetMapping
    public ResponseEntity<List<SaleDTO>> readAll() throws Exception{
        List<SaleDTO> list = service.readAll().stream().map(cat -> mapper.map(cat, SaleDTO.class)).collect(Collectors.toList());
        return new ResponseEntity<>(list, HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<SaleDTO> readById(@PathVariable("id") Integer id) throws Exception{
        Sale obj = service.readById(id);
        if(obj == null){
            throw new ModelNotFoundException("ID NOT FOUND: " + id);
        }
        return new ResponseEntity<>(mapper.map(obj, SaleDTO.class), HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<SaleDTO> create(@Valid @RequestBody SaleDTO dto) throws Exception{
        Sale obj = service.create(mapper.map(dto, Sale.class));
        return new ResponseEntity<>(mapper.map(obj, SaleDTO.class), HttpStatus.CREATED);
    }

    @PutMapping
    public ResponseEntity<SaleDTO> update(@Valid @RequestBody SaleDTO dto) throws Exception{
        Sale obj = service.readById(dto.getIdSale());

        if(obj == null){
            throw new ModelNotFoundException("ID NOT FOUND: " + dto.getIdSale());
        }
        Sale cat = service.update(mapper.map(dto, Sale.class));
        return new ResponseEntity<>(mapper.map(cat, SaleDTO.class), HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable("id") Integer id) throws Exception{
        Sale obj = service.readById(id);

        if(obj == null){
            throw new ModelNotFoundException("ID NOT FOUND: " + id);
        }
        service.delete(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    /////////////////////////////

    @GetMapping("/resume")
    public ResponseEntity<List<ProcedureDTO>> getSaleResume(){
        return new ResponseEntity<>(service.callProcedure(), HttpStatus.OK);
    }

    @GetMapping("/resume2")
    public ResponseEntity<List<ProcedureDTO>> getSaleResume2(){
        return new ResponseEntity<>(service.callProcedure2(), HttpStatus.OK);
    }

    @GetMapping("/resume3")
    public ResponseEntity<List<IProcedureDTO>> getSaleResume3(){
        return new ResponseEntity<>(service.callProcedure3(), HttpStatus.OK);
    }

    @GetMapping("/resume4/{idClient}")
    public ResponseEntity<List<IProcedureDTO>> getSaleResume4(@PathVariable("idClient") Integer idClient){
        return new ResponseEntity<>(service.callProcedure4(idClient), HttpStatus.OK);
    }
}
