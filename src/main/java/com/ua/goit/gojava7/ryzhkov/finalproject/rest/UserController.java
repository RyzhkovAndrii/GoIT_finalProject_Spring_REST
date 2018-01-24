package com.ua.goit.gojava7.ryzhkov.finalproject.rest;

import com.ua.goit.gojava7.ryzhkov.finalproject.model.User;
import com.ua.goit.gojava7.ryzhkov.finalproject.service.UserService;
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
@RequestMapping("/users")
@PreAuthorize("hasRole('ROLE_ADMIN')")
public class UserController {

    private UserService userService;

    @Autowired
    public UserController(UserService userService) {
        this.userService = userService;
    }

    @ApiOperation(value = "view list of users", response = Collection.class)
    @RequestMapping(method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public ResponseEntity<Collection<User>> getList() {
        Collection<User> list = userService.findAll();
        return !list.isEmpty()
                ? new ResponseEntity<>(list, HttpStatus.OK)
                : new ResponseEntity(HttpStatus.NO_CONTENT);
    }

    @ApiOperation(value = "search user with ID", response = User.class)
    @RequestMapping(value = "/{id}", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public ResponseEntity<User> get(@PathVariable UUID id) {
        User user = userService.findById(id);
        return user != null
                ? new ResponseEntity<>(user, HttpStatus.OK)
                : new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @ApiOperation(value = "add user", response = User.class)
    @RequestMapping(method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public ResponseEntity<User> save(@RequestBody User user) {
        userService.save(user);
        return new ResponseEntity<>(user, HttpStatus.CREATED);
    }

    @ApiOperation(value = "update user")
    @RequestMapping(value = "/{id}", method = RequestMethod.PUT, produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public ResponseEntity<Void> update(@PathVariable UUID id, @RequestBody User user) {
        User entity = userService.findById(id);
        if (entity == null) {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
        user.setId(entity.getId());
        userService.save(user);
        return new ResponseEntity<>(HttpStatus.CREATED);
    }

    @ApiOperation(value = "delete user")
    @RequestMapping(value = "/{id}", method = RequestMethod.DELETE, produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public ResponseEntity<Void> delete(@PathVariable UUID id) {
        if (userService.findById(id) == null) {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
        userService.delete(id);
        return new ResponseEntity<>(HttpStatus.OK);
    }

}
