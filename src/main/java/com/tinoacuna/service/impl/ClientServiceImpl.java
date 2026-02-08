package com.tinoacuna.service.impl;

import com.tinoacuna.model.Client;
import com.tinoacuna.repo.IClientRepo;
import com.tinoacuna.repo.IGenericRepo;
import com.tinoacuna.service.IClientService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ClientServiceImpl extends CRUDImpl<Client, Integer> implements IClientService {

    @Autowired
    private IClientRepo repo;

    @Override
    protected IGenericRepo<Client, Integer> getRepo() {
        return repo;
    }
}
