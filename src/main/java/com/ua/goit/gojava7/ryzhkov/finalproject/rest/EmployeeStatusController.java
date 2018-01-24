package com.ua.goit.gojava7.ryzhkov.finalproject.rest;

import com.ua.goit.gojava7.ryzhkov.finalproject.model.EmployeeStatus;
import com.ua.goit.gojava7.ryzhkov.finalproject.service.EmployeeStatusService;
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
@RequestMapping("/employee-statuses")
@PreAuthorize("hasAnyRole('ROLE_ADMIN', 'ROLE_MODERATOR')")
public class EmployeeStatusController {

    private EmployeeStatusService employeeStatusService;

    @Autowired
    public EmployeeStatusController(EmployeeStatusService employeeStatusService) {
        this.employeeStatusService = employeeStatusService;
    }

    @ApiOperation(value = "view list of employee statuses", response = Collection.class)
    @RequestMapping(method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public ResponseEntity<Collection<EmployeeStatus>> getList() {
        return new ResponseEntity<>(employeeStatusService.findAll(), HttpStatus.OK);
    }

    @ApiOperation(value = "search employee status with name", response = EmployeeStatus.class)
    // todo same ulr like list
    @RequestMapping(params = "name", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public ResponseEntity<EmployeeStatus> getByName(@RequestParam String name) {
        return new ResponseEntity<>(employeeStatusService.findByName(name), HttpStatus.OK);
    }

    @ApiOperation(value = "search employee status with ID", response = EmployeeStatus.class)
    @RequestMapping(value = "/{id}", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public ResponseEntity<EmployeeStatus> get(@PathVariable UUID id) {
        return new ResponseEntity<>(employeeStatusService.findById(id), HttpStatus.OK);
    }

    @ApiOperation(value = "add employee status", response = EmployeeStatus.class)
    @RequestMapping(method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public ResponseEntity<EmployeeStatus> save(@RequestBody EmployeeStatus employeeStatus) {
        return new ResponseEntity<>(employeeStatusService.save(employeeStatus), HttpStatus.CREATED);
    }

    @ApiOperation(value = "update employee status")
    @RequestMapping(value = "/{id}", method = RequestMethod.PUT, produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public ResponseEntity<Void> update(@PathVariable UUID id, @RequestBody EmployeeStatus employeeStatus) {
        employeeStatus.setId(id);
        employeeStatusService.save(employeeStatus);
        return new ResponseEntity<>(HttpStatus.CREATED);
    }

    @ApiOperation(value = "delete employee status")
    @RequestMapping(value = "/{id}", method = RequestMethod.DELETE, produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public ResponseEntity<Void> delete(@PathVariable UUID id) {
        employeeStatusService.delete(id);
        return new ResponseEntity<>(HttpStatus.OK);
    }

}
