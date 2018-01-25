package com.ua.goit.gojava7.ryzhkov.finalproject.service.impl;

import com.ua.goit.gojava7.ryzhkov.finalproject.model.Employee;
import com.ua.goit.gojava7.ryzhkov.finalproject.repository.EmployeeRepository;
import com.ua.goit.gojava7.ryzhkov.finalproject.service.EmployeeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Collection;
import java.util.UUID;

@Service
@Transactional
public class EmployeeServiceImpl implements EmployeeService {

    private EmployeeRepository employeeRepository;

    @Autowired
    public EmployeeServiceImpl(EmployeeRepository employeeRepository) {
        this.employeeRepository = employeeRepository;
    }

    @Override
    public Employee findById(UUID id) {
        return employeeRepository.findOne(id);
    }

    @Override
    public Collection<Employee> findAll() {
        return employeeRepository.findAll();
    }

    @Override
    public Employee save(Employee employee) {
        return employeeRepository.save(employee);
    }

    @Override
    public void delete(Employee employee) {
        employeeRepository.delete(employee);
    }

    @Override
    public void delete(UUID id) {
        employeeRepository.delete(id);
    }

    @Override
    public void changeCurrentWorkingHours(Employee employee, double time) {
        double currentWorkingHours = employee.getCurrentWorkingHours();
        currentWorkingHours += time;
        employee.setCurrentWorkingHours(currentWorkingHours);
        save(employee);
    }

    @Override
    public void nullableCurrentWorkingHours(Employee employee) {
        employee.setCurrentWorkingHours(0);
        save(employee);
    }

}
