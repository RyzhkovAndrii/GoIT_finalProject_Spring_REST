package com.ua.goit.gojava7.ryzhkov.finalproject.service;

import com.ua.goit.gojava7.ryzhkov.finalproject.model.Employee;

import java.util.UUID;

public interface EmployeeService extends BaseEntityService<Employee, UUID> {

    void changeCurrentWorkingHours(Employee employee, double time);

    void nullableCurrentWorkingHours(Employee employee);

}
