package com.ua.goit.gojava7.ryzhkov.finalproject.service.impl;

import com.ua.goit.gojava7.ryzhkov.finalproject.model.Department;
import com.ua.goit.gojava7.ryzhkov.finalproject.repository.DepartmentRepository;
import com.ua.goit.gojava7.ryzhkov.finalproject.service.DepartmentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.UUID;

@Service
@Transactional
public class DepartmentServiceImpl extends NamedEntityServiceImpl<Department, UUID> implements DepartmentService {

    @Autowired
    public DepartmentServiceImpl(DepartmentRepository departmentRepository) {
        super(departmentRepository);
    }

}
