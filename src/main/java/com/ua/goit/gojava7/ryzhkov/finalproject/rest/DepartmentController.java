package com.ua.goit.gojava7.ryzhkov.finalproject.rest;

import com.ua.goit.gojava7.ryzhkov.finalproject.model.Department;
import com.ua.goit.gojava7.ryzhkov.finalproject.service.DepartmentService;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Collection;
import java.util.UUID;

@RestController
@RequestMapping("/departments")
public class DepartmentController {

    private DepartmentService departmentService;

    @Autowired
    public DepartmentController(DepartmentService departmentService) {
        this.departmentService = departmentService;
    }

    @ApiOperation(value = "view list of departments", response = Collection.class)
    @RequestMapping(method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public ResponseEntity<Collection<Department>> getList() {
        Collection<Department> list = departmentService.findAll();
        return !list.isEmpty()
                ? new ResponseEntity<>(list, HttpStatus.OK)
                : new ResponseEntity(HttpStatus.NO_CONTENT);
    }

    @ApiOperation(value = "search department with name", response = Department.class) // todo same ulr like list
    @RequestMapping(params = "name", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public ResponseEntity<Department> getByName(@RequestParam String name) {
        Department department = departmentService.findByName(name);
        return department != null
                ? new ResponseEntity<>(department, HttpStatus.OK)
                : new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @ApiOperation(value = "search department with ID", response = Department.class)
    @RequestMapping(value = "/{id}", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public ResponseEntity<Department> get(@PathVariable UUID id) {
        Department department = departmentService.findById(id);
        return department != null
                ? new ResponseEntity<>(department, HttpStatus.OK)
                : new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @ApiOperation(value = "add department", response = Department.class)
    @RequestMapping(method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public ResponseEntity<Department> save(@RequestBody Department department) {
        departmentService.save(department);
        return new ResponseEntity<>(department, HttpStatus.CREATED);
    }

    @ApiOperation(value = "update department")
    @RequestMapping(value = "/{id}", method = RequestMethod.PUT, produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public ResponseEntity<Void> update(@PathVariable UUID id, @RequestBody Department department) {
        Department entity = departmentService.findById(id);
        if (entity == null) {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
        department.setId(entity.getId());
        departmentService.save(department);
        return new ResponseEntity<>(HttpStatus.CREATED);
    }

    @ApiOperation(value = "delete department")
    @RequestMapping(value = "/{id}", method = RequestMethod.DELETE, produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public ResponseEntity<Void> delete(@PathVariable UUID id) {
        if (departmentService.findById(id) == null) {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
        departmentService.delete(id);
        return new ResponseEntity<>(HttpStatus.OK);
    }

}
