package com.ua.goit.gojava7.ryzhkov.finalproject.rest;

import com.ua.goit.gojava7.ryzhkov.finalproject.model.EmployeePost;
import com.ua.goit.gojava7.ryzhkov.finalproject.service.EmployeePostService;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Collection;
import java.util.UUID;

@RestController
@RequestMapping("/employee-posts")
public class EmployeePostController {

    private EmployeePostService employeePostService;

    @Autowired
    public EmployeePostController(EmployeePostService employeePostService) {
        this.employeePostService = employeePostService;
    }

    @ApiOperation(value = "view list of employee posts", response = Collection.class)
    @RequestMapping(method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public ResponseEntity<Collection<EmployeePost>> getList() {
        Collection<EmployeePost> list = employeePostService.findAll();
        return !list.isEmpty()
                ? new ResponseEntity<>(list, HttpStatus.OK)
                : new ResponseEntity(HttpStatus.NO_CONTENT);
    }

    @ApiOperation(value = "search employee post with name", response = EmployeePost.class) // todo same ulr like list
    @RequestMapping(params = "name", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public ResponseEntity<EmployeePost> getByName(@RequestParam String name) {
        EmployeePost employeePost = employeePostService.findByName(name);
        return employeePost != null
                ? new ResponseEntity<>(employeePost, HttpStatus.OK)
                : new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @ApiOperation(value = "search employee post with ID", response = EmployeePost.class)
    @RequestMapping(value = "/{id}", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public ResponseEntity<EmployeePost> get(@PathVariable UUID id) {
        EmployeePost employeePost = employeePostService.findById(id);
        return employeePost != null
                ? new ResponseEntity<>(employeePost, HttpStatus.OK)
                : new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @ApiOperation(value = "add employee post", response = EmployeePost.class)
    @RequestMapping(method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public ResponseEntity<EmployeePost> save(@RequestBody EmployeePost employeePost) {
        employeePostService.save(employeePost);
        return new ResponseEntity<>(employeePost, HttpStatus.CREATED);
    }

    @ApiOperation(value = "update employee post")
    @RequestMapping(value = "/{id}", method = RequestMethod.PUT, produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public ResponseEntity<Void> update(@PathVariable UUID id, @RequestBody EmployeePost employeePost) {
        EmployeePost entity = employeePostService.findById(id);
        if (entity == null) {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
        employeePost.setId(entity.getId());
        employeePostService.save(employeePost);
        return new ResponseEntity<>(HttpStatus.CREATED);
    }

    @ApiOperation(value = "delete employee post")
    @RequestMapping(value = "/{id}", method = RequestMethod.DELETE, produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public ResponseEntity<Void> delete(@PathVariable UUID id) {
        if (employeePostService.findById(id) == null) {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
        employeePostService.delete(id);
        return new ResponseEntity<>(HttpStatus.OK);
    }

}
