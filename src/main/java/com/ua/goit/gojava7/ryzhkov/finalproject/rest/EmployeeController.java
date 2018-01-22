package com.ua.goit.gojava7.ryzhkov.finalproject.rest;

import com.ua.goit.gojava7.ryzhkov.finalproject.model.Employee;
import com.ua.goit.gojava7.ryzhkov.finalproject.service.EmployeeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.UUID;

@RestController
@RequestMapping(value = "/employees")
public class EmployeeController extends BaseEntityController<Employee, UUID> {

    @Autowired
    public EmployeeController(EmployeeService employeeService) {
        super(employeeService);
    }

}
