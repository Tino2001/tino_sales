package com.tinoacuna.controller;

import com.tinoacuna.dto.ClientDTO;
import com.tinoacuna.exception.ModelNotFoundException;
import com.tinoacuna.model.Client;
import com.tinoacuna.service.IClientService;
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
@RequestMapping("/clients")
public class ClientController {

    @Autowired
    private IClientService service;

    @Autowired
    @Qualifier("clientMapper")
    private ModelMapper mapper;

    @GetMapping
    public ResponseEntity<List<ClientDTO>> readAll() throws Exception{
        List<ClientDTO> list = service.readAll().stream().map(cat -> mapper.map(cat, ClientDTO.class)).collect(Collectors.toList());
        return new ResponseEntity<>(list, HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<ClientDTO> readById(@PathVariable("id") Integer id) throws Exception{
        Client obj = service.readById(id);
        if(obj == null){
            throw new ModelNotFoundException("ID NOT FOUND: " + id);
        }
        return new ResponseEntity<>(mapper.map(obj, ClientDTO.class), HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<ClientDTO> create(@Valid @RequestBody ClientDTO dto) throws Exception{
        Client obj = service.create(mapper.map(dto, Client.class));
        return new ResponseEntity<>(mapper.map(obj, ClientDTO.class), HttpStatus.CREATED);
    }

    @PutMapping
    public ResponseEntity<ClientDTO> update(@Valid @RequestBody ClientDTO dto) throws Exception{
        Client obj = service.readById(dto.getIdClient());

        if(obj == null){
            throw new ModelNotFoundException("ID NOT FOUND: " + dto.getIdClient());
        }
        Client cat = service.update(mapper.map(dto, Client.class));
        return new ResponseEntity<>(mapper.map(cat, ClientDTO.class), HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable("id") Integer id) throws Exception{
        Client obj = service.readById(id);

        if(obj == null){
            throw new ModelNotFoundException("ID NOT FOUND: " + id);
        }
        service.delete(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

}
