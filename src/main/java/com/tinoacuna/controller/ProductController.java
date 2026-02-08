package com.tinoacuna.controller;

import com.tinoacuna.dto.ProductDTO;
import com.tinoacuna.exception.ModelNotFoundException;
import com.tinoacuna.model.Product;
import com.tinoacuna.service.IProductService;
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
@RequestMapping("/products")
public class ProductController {

    @Autowired
    private IProductService service;

    @Autowired
    @Qualifier("productMapper")
    private ModelMapper mapper;

    @GetMapping
    public ResponseEntity<List<ProductDTO>> readAll() throws Exception{
        List<ProductDTO> list = service.readAll().stream().map(prod -> mapper.map(prod, ProductDTO.class)).collect(Collectors.toList());
        return new ResponseEntity<>(list, HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<ProductDTO> readById(@PathVariable("id") Integer id) throws Exception{
        Product obj = service.readById(id);
        if(obj == null){
            throw new ModelNotFoundException("ID NOT FOUND: " + id);
        }
        return new ResponseEntity<>(mapper.map(obj, ProductDTO.class), HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<ProductDTO> create(@Valid @RequestBody ProductDTO dto) throws Exception{
        Product obj = service.create(mapper.map(dto, Product.class));
        return new ResponseEntity<>(mapper.map(obj, ProductDTO.class), HttpStatus.CREATED);
    }

    @PutMapping
    public ResponseEntity<ProductDTO> update(@Valid @RequestBody ProductDTO dto) throws Exception{
        Product obj = service.readById(dto.getIdProduct());

        if(obj == null){
            throw new ModelNotFoundException("ID NOT FOUND: " + dto.getIdProduct());
        }
        Product prod = service.update(mapper.map(dto, Product.class));
        return new ResponseEntity<>(mapper.map(prod, ProductDTO.class), HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable("id") Integer id) throws Exception{
        Product obj = service.readById(id);

        if(obj == null){
            throw new ModelNotFoundException("ID NOT FOUND: " + id);
        }
        service.delete(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

}
