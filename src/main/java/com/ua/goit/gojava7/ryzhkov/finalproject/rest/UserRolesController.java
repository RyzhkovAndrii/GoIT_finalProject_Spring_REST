package com.ua.goit.gojava7.ryzhkov.finalproject.rest;

import com.ua.goit.gojava7.ryzhkov.finalproject.model.Role;
import com.ua.goit.gojava7.ryzhkov.finalproject.model.User;
import com.ua.goit.gojava7.ryzhkov.finalproject.service.RoleService;
import com.ua.goit.gojava7.ryzhkov.finalproject.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.Collection;
import java.util.HashSet;
import java.util.Set;
import java.util.UUID;

@RestController
@RequestMapping(value = "/users/{user}/roles")
@PreAuthorize("hasRole('ROLE_ADMIN')")
public class UserRolesController {

    private final UserService userService;

    private final RoleService roleService;

    @Autowired
    public UserRolesController(UserService userService, RoleService roleService) {
        this.userService = userService;
        this.roleService = roleService;
    }

    @RequestMapping(method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public ResponseEntity<Collection<Role>> getUserRoles(@PathVariable("user") UUID id) {
        User user = userService.findById(id);
        if (user == null) {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
        Collection<Role> userRoles = user.getRoles();
        return userRoles.isEmpty()
                ? new ResponseEntity<>(HttpStatus.NO_CONTENT)
                : new ResponseEntity<>(userRoles, HttpStatus.OK); // todo optimized
    }

    @RequestMapping(value = "/{role}", method = RequestMethod.PUT, produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public ResponseEntity<Role> addUserRole(@PathVariable("user") UUID userId,
                                            @PathVariable("role") UUID roleId) {
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
        newUserRoles.add(role); // todo optimized
        user.setRoles(newUserRoles);
        userService.save(user);
        return new ResponseEntity<>(HttpStatus.CREATED);
    }

    @RequestMapping(value = "/{role}", method = RequestMethod.DELETE, produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public ResponseEntity<Void> deleteUserRole(@PathVariable("user") UUID userId,
                                               @PathVariable("role") UUID roleId) {
        User user = userService.findById(userId);
        Role role = roleService.findById(roleId);
        if (user == null || role == null || !user.getRoles().contains(role)) {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
        Collection<Role> userRoles = user.getRoles();
        Set<Role> newUserRoles = new HashSet<>();
        newUserRoles.addAll(userRoles);
        newUserRoles.remove(role); // todo optimized
        user.setRoles(newUserRoles);
        userService.save(user);
        return new ResponseEntity<>(HttpStatus.OK);
    }

}
