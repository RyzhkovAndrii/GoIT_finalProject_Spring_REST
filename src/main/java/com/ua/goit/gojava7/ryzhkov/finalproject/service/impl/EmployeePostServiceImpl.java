package com.ua.goit.gojava7.ryzhkov.finalproject.service.impl;

import com.ua.goit.gojava7.ryzhkov.finalproject.model.EmployeePost;
import com.ua.goit.gojava7.ryzhkov.finalproject.repository.EmployeePostRepository;
import com.ua.goit.gojava7.ryzhkov.finalproject.service.EmployeePostService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.UUID;

@Service
@Transactional
public class EmployeePostServiceImpl extends NamedEntityServiceImpl<EmployeePost, UUID>
        implements EmployeePostService {

    @Autowired
    public EmployeePostServiceImpl(EmployeePostRepository employeePostRepository) {
        super(employeePostRepository);
    }

}
