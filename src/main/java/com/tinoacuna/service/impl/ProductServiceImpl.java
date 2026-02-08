package com.tinoacuna.service.impl;

import com.tinoacuna.model.Product;
import com.tinoacuna.repo.ICategoryRepo;
import com.tinoacuna.repo.IGenericRepo;
import com.tinoacuna.repo.IProductRepo;
import com.tinoacuna.service.ICategoryService;
import com.tinoacuna.service.IProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ProductServiceImpl extends CRUDImpl<Product, Integer> implements IProductService {

    @Autowired
    private IProductRepo repo;

    @Override
    protected IGenericRepo<Product, Integer> getRepo() {
        return repo;
    }

}
