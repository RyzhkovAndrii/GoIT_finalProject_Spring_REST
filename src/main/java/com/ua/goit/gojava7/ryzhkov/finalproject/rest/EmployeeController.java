package com.ua.goit.gojava7.ryzhkov.finalproject.rest;

import com.ua.goit.gojava7.ryzhkov.finalproject.model.Employee;
import com.ua.goit.gojava7.ryzhkov.finalproject.service.EmployeeService;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.Collection;
import java.util.UUID;

@RestController
@RequestMapping("/employees")
@PreAuthorize("hasAnyRole('ROLE_ADMIN', 'ROLE_MODERATOR')")
public class EmployeeController {

    private EmployeeService employeeService;

    @Autowired
    public EmployeeController(EmployeeService employeeService) {
        this.employeeService = employeeService;
    }

    @ApiOperation(value = "view list of employees", response = Collection.class)
    @RequestMapping(method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public ResponseEntity<Collection<Employee>> getList() {
        Collection<Employee> list = employeeService.findAll();
        return !list.isEmpty()
                ? new ResponseEntity<>(list, HttpStatus.OK)
                : new ResponseEntity(HttpStatus.NO_CONTENT);
    }

    @PreAuthorize("hasAnyRole('ROLE_ADMIN', 'ROLE_MODERATOR', 'ROLE_USER')") // todo only one user
    @ApiOperation(value = "search employee with ID", response = Employee.class)
    @RequestMapping(value = "/{id}", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public ResponseEntity<Employee> get(@PathVariable UUID id) {
        Employee employee = employeeService.findById(id);
        return employee != null
                ? new ResponseEntity<>(employee, HttpStatus.OK)
                : new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @ApiOperation(value = "add employee", response = Employee.class)
    @RequestMapping(method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public ResponseEntity<Employee> save(@RequestBody Employee employee) {
        employeeService.save(employee);
        return new ResponseEntity<>(employee, HttpStatus.CREATED);
    }

    @ApiOperation(value = "update employee")
    @RequestMapping(value = "/{id}", method = RequestMethod.PUT, produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public ResponseEntity<Void> update(@PathVariable UUID id, @RequestBody Employee employee) {
        Employee entity = employeeService.findById(id);
        if (entity == null) {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
        employee.setId(entity.getId());
        employeeService.save(employee);
        return new ResponseEntity<>(HttpStatus.CREATED);
    }

    @ApiOperation(value = "delete employee")
    @RequestMapping(value = "/{id}", method = RequestMethod.DELETE, produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public ResponseEntity<Void> delete(@PathVariable UUID id) {
        if (employeeService.findById(id) == null) {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
        employeeService.delete(id);
        return new ResponseEntity<>(HttpStatus.OK);
    }

}
