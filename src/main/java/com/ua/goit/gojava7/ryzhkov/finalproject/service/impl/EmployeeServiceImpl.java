package com.ua.goit.gojava7.ryzhkov.finalproject.service.impl;

import com.ua.goit.gojava7.ryzhkov.finalproject.model.Employee;
import com.ua.goit.gojava7.ryzhkov.finalproject.repository.EmployeeRepository;
import com.ua.goit.gojava7.ryzhkov.finalproject.service.EmployeeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.UUID;

@Service
@Transactional
public class EmployeeServiceImpl extends BaseEntityServiceImpl<Employee, UUID> implements EmployeeService {

    @Autowired
    public EmployeeServiceImpl(EmployeeRepository employeeRepository) {
        super(employeeRepository);
    }

}
