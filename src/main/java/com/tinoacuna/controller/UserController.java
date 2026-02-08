package com.tinoacuna.controller;

import com.tinoacuna.dto.UserDTO;
import com.tinoacuna.exception.ModelNotFoundException;
import com.tinoacuna.model.User;
import com.tinoacuna.service.IUserService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/users")
public class UserController {

    @Autowired
    private IUserService service;

    @Autowired
    @Qualifier("roleMapper")
    private ModelMapper mapper;

    @GetMapping
    public ResponseEntity<List<UserDTO>> readAll() throws Exception{
        List<UserDTO> list = service.readAll().stream().map(cat -> mapper.map(cat, UserDTO.class)).collect(Collectors.toList());
        return new ResponseEntity<>(list, HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<UserDTO> readById(@PathVariable("id") Integer id) throws Exception{
        User obj = service.readById(id);
        if(obj == null){
            throw new ModelNotFoundException("ID NOT FOUND: " + id);
        }
        return new ResponseEntity<>(mapper.map(obj, UserDTO.class), HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<UserDTO> create(@Valid @RequestBody UserDTO dto) throws Exception{
        User obj = service.create(mapper.map(dto, User.class));
        return new ResponseEntity<>(mapper.map(obj, UserDTO.class), HttpStatus.CREATED);
    }

    @PutMapping
    public ResponseEntity<UserDTO> update(@Valid @RequestBody UserDTO dto) throws Exception{
        User obj = service.readById(dto.getIdUser());

        if(obj == null){
            throw new ModelNotFoundException("ID NOT FOUND: " + dto.getIdUser());
        }
        User cat = service.update(mapper.map(dto, User.class));
        return new ResponseEntity<>(mapper.map(cat, UserDTO.class), HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable("id") Integer id) throws Exception{
        User obj = service.readById(id);

        if(obj == null){
            throw new ModelNotFoundException("ID NOT FOUND: " + id);
        }
        service.delete(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

}
