package com.ua.goit.gojava7.ryzhkov.finalproject.rest;

import com.ua.goit.gojava7.ryzhkov.finalproject.model.EmployeeStatus;
import com.ua.goit.gojava7.ryzhkov.finalproject.service.EmployeeStatusService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.UUID;

@RestController
@RequestMapping(value = "/employee-statuses")
public class EmployeeStatusController extends NamedEntityController<EmployeeStatus, UUID> {

    @Autowired
    public EmployeeStatusController(EmployeeStatusService employeeStatusService) {
        super(employeeStatusService);
    }
}
