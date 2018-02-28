package com.ua.goit.gojava7.ryzhkov.finalproject.rest;

import com.ua.goit.gojava7.ryzhkov.finalproject.converter.ModelConversionService;
import com.ua.goit.gojava7.ryzhkov.finalproject.dto.EmployeeRequest;
import com.ua.goit.gojava7.ryzhkov.finalproject.dto.EmployeeResponse;
import com.ua.goit.gojava7.ryzhkov.finalproject.model.Employee;
import com.ua.goit.gojava7.ryzhkov.finalproject.service.EmployeeService;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.Collection;
import java.util.UUID;

@RestController
@RequestMapping("/employees")
@PreAuthorize("hasAnyRole('ROLE_ADMIN', 'ROLE_MODERATOR')")
public class EmployeeController {

    private EmployeeService employeeService;

    private ModelConversionService conversionService;

    @Autowired
    public EmployeeController(EmployeeService employeeService, ModelConversionService conversionService) {
        this.employeeService = employeeService;
        this.conversionService = conversionService;
    }

    @ApiOperation(value = "view list of employees")
    @RequestMapping(method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    @ResponseStatus(HttpStatus.OK)
    public Collection<EmployeeResponse> getList() {
        return conversionService.convert(employeeService.findAll(), EmployeeResponse.class);
    }

    @PreAuthorize("hasAnyRole('ROLE_ADMIN', 'ROLE_MODERATOR', 'ROLE USER')")
//            "or #id == @securityServiceImpl.findLoggedInEmployeeId()") // todo not working
    @ApiOperation(value = "search employee with ID")
    @RequestMapping(value = "/{id}", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    @ResponseStatus(HttpStatus.OK)
    public EmployeeResponse get(@PathVariable UUID id) {
        return conversionService.convert(employeeService.findById(id), EmployeeResponse.class);
    }

    @ApiOperation(value = "add employee", response = Employee.class)
    @RequestMapping(method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    @ResponseStatus(HttpStatus.CREATED)
    public EmployeeResponse save(@RequestBody EmployeeRequest employeeRequest) {
        Employee employee = conversionService.convert(employeeRequest, Employee.class);
        return conversionService.convert(employeeService.save(employee), EmployeeResponse.class);
    }

    @ApiOperation(value = "update employee")
    @RequestMapping(value = "/{id}", method = RequestMethod.PUT, produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    @ResponseStatus(HttpStatus.OK)
    public void update(@PathVariable UUID id, @RequestBody EmployeeRequest employeeRequest) {
        Employee employee = conversionService.convert(employeeRequest, Employee.class);
        employee.setId(id);
        employeeService.save(employee);
    }

    @ApiOperation(value = "delete employee")
    @RequestMapping(value = "/{id}", method = RequestMethod.DELETE, produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    @ResponseStatus(HttpStatus.OK)
    public void delete(@PathVariable UUID id) {
        employeeService.delete(id);
    }

}
