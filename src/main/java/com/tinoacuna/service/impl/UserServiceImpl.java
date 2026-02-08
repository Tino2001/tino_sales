package com.tinoacuna.service.impl;

import com.tinoacuna.model.User;
import com.tinoacuna.repo.IGenericRepo;
import com.tinoacuna.repo.IUserRepo;
import com.tinoacuna.service.IUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImpl extends CRUDImpl<User, Integer> implements IUserService {

    @Autowired
    private IUserRepo repo;

    @Override
    protected IGenericRepo<User, Integer> getRepo() {
        return repo;
    }
}
