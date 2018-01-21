package com.ua.goit.gojava7.ryzhkov.finalproject.service.impl;

import com.ua.goit.gojava7.ryzhkov.finalproject.model.Role;
import com.ua.goit.gojava7.ryzhkov.finalproject.repository.RoleRepository;
import com.ua.goit.gojava7.ryzhkov.finalproject.service.RoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.UUID;

@Service
@Transactional
public class RoleServiceImpl extends NamedEntityServiceImpl<Role, UUID> implements RoleService {

    @Autowired
    public RoleServiceImpl(RoleRepository roleRepository) {
        super(roleRepository);
    }

}
