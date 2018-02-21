package com.ua.goit.gojava7.ryzhkov.finalproject.rest;

import com.ua.goit.gojava7.ryzhkov.finalproject.model.Employee;
import com.ua.goit.gojava7.ryzhkov.finalproject.service.EmployeeService;
import com.ua.goit.gojava7.ryzhkov.finalproject.service.UserService;
import io.swagger.annotations.ApiOperation;
import org.modelmapper.ModelMapper;
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

    private UserService userService;

    private ModelMapper modelMapper;

    @Autowired
    public EmployeeController(EmployeeService employeeService, ModelMapper modelMapper) {
        this.employeeService = employeeService;
        this.modelMapper = modelMapper;
    }

    @ApiOperation(value = "view list of employees", response = Collection.class)
    @RequestMapping(method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public ResponseEntity<Collection<Employee>> getList() {
        return new ResponseEntity<>(employeeService.findAll(), HttpStatus.OK);
    }

    @PreAuthorize("hasAnyRole('ROLE_ADMIN', 'ROLE_MODERATOR', 'ROLE USER')")
//            "or #id == @securityServiceImpl.findLoggedInEmployeeId()") // todo not working
    @ApiOperation(value = "search employee with ID", response = Employee.class)
    @RequestMapping(value = "/{id}", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public ResponseEntity<Employee> get(@PathVariable UUID id) {
        return new ResponseEntity<>(employeeService.findById(id), HttpStatus.OK);
    }

    @ApiOperation(value = "add employee", response = Employee.class)
    @RequestMapping(method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public ResponseEntity<Employee> save(@RequestBody Employee employee) {
        return new ResponseEntity<>(employeeService.save(employee), HttpStatus.CREATED);
    }

    @ApiOperation(value = "update employee")
    @RequestMapping(value = "/{id}", method = RequestMethod.PUT, produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public ResponseEntity<Void> update(@PathVariable UUID id, @RequestBody Employee employee) {
        employee.setId(id);
        employeeService.save(employee);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @ApiOperation(value = "delete employee")
    @RequestMapping(value = "/{id}", method = RequestMethod.DELETE, produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public ResponseEntity<Void> delete(@PathVariable UUID id) {
        employeeService.delete(id);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    /*private Employee convertToEntity(EmployeeSaveDto dto) {
        Employee employee = modelMapper.map(dto, Employee.class);
        if (dto.getUserId() != null) {
            employee.setUser(userService.findById(dto.getUserId()));
        }
        return employee;
    }

    private EmployeeDto convertToDto(Employee employee) {
        return modelMapper.map(employee, EmployeeDto.class);
    }

    private Collection<EmployeeDto> convertToDto(Collection<Employee> employees) {
        return employees.stream().map(this::convertToDto).collect(Collectors.toList());
    }*/

}
