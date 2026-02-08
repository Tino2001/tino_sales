package com.tinoacuna.service;

import com.tinoacuna.model.Category;

import java.util.List;

public interface ICategoryService extends ICRUD<Category, Integer>{

    List<Category> findByName(String name);
    List<Category> findByNameAndEnabled(String name, boolean enabled);

    List<Category> getByNameAndDescription1(String name, String description);

    List<Category> getByNameAndDescription3();

}
