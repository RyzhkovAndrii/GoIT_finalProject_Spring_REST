package com.ua.goit.gojava7.ryzhkov.finalproject.rest;

import com.ua.goit.gojava7.ryzhkov.finalproject.model.EmployeePost;
import com.ua.goit.gojava7.ryzhkov.finalproject.service.EmployeePostService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.UUID;

@RestController
@RequestMapping(value = "/employee-posts")
public class EmployeePostController extends NamedEntityController<EmployeePost, UUID> {

    @Autowired
    public EmployeePostController(EmployeePostService employeePostService) {
        super(employeePostService);
    }

}
