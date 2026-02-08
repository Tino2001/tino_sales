package com.tinoacuna.controller;

import com.tinoacuna.dto.CategoryDTO;
import com.tinoacuna.exception.ModelNotFoundException;
import com.tinoacuna.model.Category;
import com.tinoacuna.service.ICategoryService;
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
@RequestMapping("/categories")
public class CategoryController {

    @Autowired
    private ICategoryService service;

    @Autowired
    @Qualifier("categoryMapper")
    private ModelMapper mapper;

    @GetMapping
    public ResponseEntity<List<CategoryDTO>> readAll() throws Exception{
        List<CategoryDTO> list = service.readAll().stream().map(cat -> mapper.map(cat, CategoryDTO.class)).collect(Collectors.toList());
        return new ResponseEntity<>(list, HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<CategoryDTO> readById(@PathVariable("id") Integer id) throws Exception{
        Category obj = service.readById(id);
        if(obj == null){
            throw new ModelNotFoundException("ID NOT FOUND: " + id);
        }
        return new ResponseEntity<>(mapper.map(obj, CategoryDTO.class), HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<CategoryDTO> create(@Valid @RequestBody CategoryDTO dto) throws Exception{
        Category obj = service.create(mapper.map(dto, Category.class));
        return new ResponseEntity<>(mapper.map(obj, CategoryDTO.class), HttpStatus.CREATED);
    }

    @PutMapping
    public ResponseEntity<CategoryDTO> update(@Valid @RequestBody CategoryDTO dto) throws Exception{
        Category obj = service.readById(dto.getId());

        if(obj == null){
            throw new ModelNotFoundException("ID NOT FOUND: " + dto.getId());
        }
        Category cat = service.update(mapper.map(dto, Category.class));
        return new ResponseEntity<>(mapper.map(cat, CategoryDTO.class), HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable("id") Integer id) throws Exception{
        Category obj = service.readById(id);

        if(obj == null){
            throw new ModelNotFoundException("ID NOT FOUND: " + id);
        }
        service.delete(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    /////////////////////queries//////////////////

    @GetMapping("/find/name/{name}")
    public ResponseEntity<List<CategoryDTO>> findByName(@PathVariable("name") String name){
        List<CategoryDTO> lst = service.findByName(name).stream().map(cat -> mapper.map(cat, CategoryDTO.class)).collect(Collectors.toList());
        return new ResponseEntity<>(lst, HttpStatus.OK);
    }

    @GetMapping("/find/name")
    public ResponseEntity<List<CategoryDTO>> findByNameAndEnabled(@RequestParam("name") String name, @RequestParam("status") boolean status) throws Exception {
        List<CategoryDTO> lst = service.findByNameAndEnabled(name, status).stream().map(cat -> mapper.map(cat, CategoryDTO.class)).collect(Collectors.toList());

        return new ResponseEntity<>(lst, HttpStatus.OK);
    }

    @GetMapping("/get/name/description")
    public ResponseEntity<List<CategoryDTO>> getByNameAndDescription() throws Exception {
        List<CategoryDTO> lst = service.getByNameAndDescription1("COMPUTERS", "To many").stream().map(cat -> mapper.map(cat, CategoryDTO.class)).collect(Collectors.toList());

        return new ResponseEntity<>(lst, HttpStatus.OK);
    }

    @GetMapping("/get/name/description3")
    public ResponseEntity<List<CategoryDTO>> getByNameAndDescription3() throws Exception {
        List<CategoryDTO> lst = service.getByNameAndDescription3().stream().map(cat -> mapper.map(cat, CategoryDTO.class)).collect(Collectors.toList());

        return new ResponseEntity<>(lst, HttpStatus.OK);
    }

}
