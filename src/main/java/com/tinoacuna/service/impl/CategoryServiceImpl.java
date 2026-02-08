package com.tinoacuna.service.impl;

import com.tinoacuna.model.Category;
import com.tinoacuna.repo.ICategoryRepo;
import com.tinoacuna.repo.IGenericRepo;
import com.tinoacuna.service.ICategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CategoryServiceImpl extends CRUDImpl<Category, Integer> implements ICategoryService {

    @Autowired
    private ICategoryRepo repo;

    @Override
    protected IGenericRepo<Category, Integer> getRepo() {
        return repo;
    }

    @Override
    public List<Category> findByName(String name){
        return repo.findByNameContains(name);
        //return repo.findByNameLike("%" + name + "%");
    }

    @Override
    public List<Category> findByNameAndEnabled(String name, boolean enabled){
        return repo.findByNameAndEnabled(name, enabled);
    }

    @Override
    public List<Category> getByNameAndDescription1(String name, String description) {
        return repo.getByNameAndDescription1(name, description);
    }

    @Override
    public List<Category> getByNameAndDescription3() {
        return repo.getByNameAndDescription3();
    }


}
