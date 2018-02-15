package com.ua.goit.gojava7.ryzhkov.finalproject.service.impl;

import com.ua.goit.gojava7.ryzhkov.finalproject.model.EmployeePost;
import com.ua.goit.gojava7.ryzhkov.finalproject.repository.EmployeePostRepository;
import com.ua.goit.gojava7.ryzhkov.finalproject.service.EmployeePostService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Collection;
import java.util.UUID;

@Service
@Transactional
public class EmployeePostServiceImpl implements EmployeePostService {

    private EmployeePostRepository employeePostRepository;

    @Autowired
    public EmployeePostServiceImpl(EmployeePostRepository employeePostRepository) {
        this.employeePostRepository = employeePostRepository;
    }

    @Override
    public EmployeePost findById(UUID id) {
        return employeePostRepository.findOne(id);
    }

    @Override
    public Collection<EmployeePost> findAll() {
        return employeePostRepository.findAll();
    }

    @Override
    public EmployeePost save(EmployeePost employeePost) {
        return employeePostRepository.save(employeePost);
    }

    @Override
    public void update(EmployeePost employeePost) {
        this.save(employeePost);
    }

    @Override
    public void delete(EmployeePost employeePost) {
        employeePostRepository.delete(employeePost);
    }

    @Override
    public void delete(UUID id) {
        employeePostRepository.delete(id);
    }

    @Override
    public EmployeePost findByName(String name) {
        return employeePostRepository.findByName(name);
    }

}
