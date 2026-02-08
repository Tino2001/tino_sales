package com.tinoacuna.service.impl;

import com.tinoacuna.model.Provider;
import com.tinoacuna.repo.IProviderRepo;
import com.tinoacuna.repo.IGenericRepo;
import com.tinoacuna.service.IProviderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ProviderServiceImpl extends CRUDImpl<Provider, Integer> implements IProviderService {

    @Autowired
    private IProviderRepo repo;

    @Override
    protected IGenericRepo<Provider, Integer> getRepo() {
        return repo;
    }

}
