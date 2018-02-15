package com.ua.goit.gojava7.ryzhkov.finalproject.service.impl;

import com.ua.goit.gojava7.ryzhkov.finalproject.model.EmployeeStatus;
import com.ua.goit.gojava7.ryzhkov.finalproject.repository.EmployeeStatusRepository;
import com.ua.goit.gojava7.ryzhkov.finalproject.service.EmployeeStatusService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Collection;
import java.util.UUID;

@Service
@Transactional
public class EmployeeStatusServiceImpl implements EmployeeStatusService {

    private EmployeeStatusRepository employeeStatusRepository;

    @Autowired
    public EmployeeStatusServiceImpl(EmployeeStatusRepository employeeStatusRepository) {
        this.employeeStatusRepository = employeeStatusRepository;
    }

    @Override
    public EmployeeStatus findById(UUID id) {
        return employeeStatusRepository.findOne(id);
    }

    @Override
    public Collection<EmployeeStatus> findAll() {
        return employeeStatusRepository.findAll();
    }

    @Override
    public EmployeeStatus save(EmployeeStatus employeeStatus) {
        return employeeStatusRepository.save(employeeStatus);
    }

    @Override
    public void update(EmployeeStatus employeeStatus) {
        this.save(employeeStatus);
    }

    @Override
    public void delete(EmployeeStatus employeeStatus) {
        employeeStatusRepository.delete(employeeStatus);
    }

    @Override
    public void delete(UUID id) {
        employeeStatusRepository.delete(id);
    }

    @Override
    public EmployeeStatus findByName(String name) {
        return employeeStatusRepository.findByName(name);
    }

}
