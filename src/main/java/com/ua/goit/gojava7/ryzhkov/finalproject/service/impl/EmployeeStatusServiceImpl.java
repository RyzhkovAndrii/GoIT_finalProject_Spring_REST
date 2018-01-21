package com.ua.goit.gojava7.ryzhkov.finalproject.service.impl;

import com.ua.goit.gojava7.ryzhkov.finalproject.model.EmployeeStatus;
import com.ua.goit.gojava7.ryzhkov.finalproject.repository.EmployeeStatusRepository;
import com.ua.goit.gojava7.ryzhkov.finalproject.service.EmployeeStatusService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.UUID;

@Service
@Transactional
public class EmployeeStatusServiceImpl extends NamedEntityServiceImpl<EmployeeStatus, UUID>
        implements EmployeeStatusService {

    @Autowired
    public EmployeeStatusServiceImpl(EmployeeStatusRepository employeeStatusRepository) {
        super(employeeStatusRepository);
    }
}
