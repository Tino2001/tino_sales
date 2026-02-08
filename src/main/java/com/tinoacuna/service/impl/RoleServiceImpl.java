package com.tinoacuna.service.impl;

import com.tinoacuna.model.Role;
import com.tinoacuna.repo.IRoleRepo;
import com.tinoacuna.repo.IGenericRepo;
import com.tinoacuna.service.IRoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class RoleServiceImpl extends CRUDImpl<Role, Integer> implements IRoleService {

    @Autowired
    private IRoleRepo repo;

    @Override
    protected IGenericRepo<Role, Integer> getRepo() {
        return repo;
    }
}
