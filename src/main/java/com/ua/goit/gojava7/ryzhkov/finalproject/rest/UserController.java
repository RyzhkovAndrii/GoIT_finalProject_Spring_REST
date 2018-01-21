package com.ua.goit.gojava7.ryzhkov.finalproject.rest;

import com.ua.goit.gojava7.ryzhkov.finalproject.model.Role;
import com.ua.goit.gojava7.ryzhkov.finalproject.model.User;
import com.ua.goit.gojava7.ryzhkov.finalproject.service.RoleService;
import com.ua.goit.gojava7.ryzhkov.finalproject.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Collection;
import java.util.HashSet;
import java.util.Set;
import java.util.UUID;

@RestController
@RequestMapping(value = "/users")
public class UserController {

    @Autowired
    private UserService userService;

    @Autowired
    private RoleService roleService;

    @RequestMapping(method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public ResponseEntity<Collection<User>> getListUsers() {
        return new ResponseEntity<>(userService.findAll(), HttpStatus.OK);
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public ResponseEntity<User> getUser(@PathVariable("id") UUID id) {
        User user = userService.findById(id);
        return user != null
                ? new ResponseEntity<>(user, HttpStatus.OK)
                : new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @RequestMapping(method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public ResponseEntity<User> saveUser(@RequestBody User user) {
        return new ResponseEntity<>(userService.save(user), HttpStatus.CREATED);
    }

    // todo put method

    @RequestMapping(value = "/{id}", method = RequestMethod.DELETE, produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public ResponseEntity<Void> deleteUser(@PathVariable("id") UUID id) {
        userService.delete(id);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @RequestMapping(value = "/{id}/roles", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public ResponseEntity<Collection<Role>> getUserRoles(@PathVariable("id") UUID id) {
        User user = userService.findById(id);
        if (user == null) {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
        Collection<Role> userRoles = user.getRoles();
        return userRoles.isEmpty()
                ? new ResponseEntity<>(HttpStatus.NO_CONTENT)
                : new ResponseEntity<>(userRoles, HttpStatus.OK); // todo not optimal
    }

    @RequestMapping(value = "/{userId}/roles/{roleId}", method = RequestMethod.PUT, produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public ResponseEntity<Role> addUserRole(@PathVariable("userId") UUID userId,
                                            @PathVariable("roleId") UUID roleId) {
        User user = userService.findById(userId);
        Role role = roleService.findById(roleId);
        if (user == null || role == null) {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
        Collection<Role> userRoles = user.getRoles();
        if (userRoles.contains(role)) {
            return new ResponseEntity<>(HttpStatus.OK); // todo another message
        }
        Set<Role> newUserRoles = new HashSet<>();
        newUserRoles.addAll(userRoles);
        newUserRoles.add(role);
        user.setRoles(newUserRoles);
        userService.save(user); // todo without newUserRoles
        return new ResponseEntity<>(HttpStatus.CREATED); // todo not optimal
    }

    @RequestMapping(value = "/{userId}/roles/{roleId}", method = RequestMethod.DELETE, produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public ResponseEntity<Void> deleteUserRole(@PathVariable("userId") UUID userId,
                                               @PathVariable("roleId") UUID roleId) {
        User user = userService.findById(userId);
        Role role = roleService.findById(roleId);
        if (user == null || role == null || !user.getRoles().contains(role)) {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
        Collection<Role> userRoles = user.getRoles();
        Set<Role> newUserRoles = new HashSet<>();
        newUserRoles.addAll(userRoles);
        newUserRoles.remove(role);
        user.setRoles(newUserRoles);
        userService.save(user); // todo without newUserRoles
        return new ResponseEntity<>(HttpStatus.OK); // todo not optimal
    }

}
