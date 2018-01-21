package com.ua.goit.gojava7.ryzhkov.finalproject.rest;

import com.ua.goit.gojava7.ryzhkov.finalproject.model.Department;
import com.ua.goit.gojava7.ryzhkov.finalproject.service.DepartmentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.UUID;

@RestController
@RequestMapping(value = "/departments")
public class DepartmentController extends NamedEntityController<Department, UUID> {

    @Autowired
    public DepartmentController(DepartmentService departmentService) {
        super(departmentService);
    }

}
