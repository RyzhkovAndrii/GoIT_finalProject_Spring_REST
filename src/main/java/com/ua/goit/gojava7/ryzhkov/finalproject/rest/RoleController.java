package com.ua.goit.gojava7.ryzhkov.finalproject.rest;

import com.ua.goit.gojava7.ryzhkov.finalproject.model.Role;
import com.ua.goit.gojava7.ryzhkov.finalproject.service.RoleService;
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
@RequestMapping("/roles")
@PreAuthorize("hasRole('ROLE_ADMIN')")
public class RoleController {

    private RoleService roleService;

    @Autowired
    public RoleController(RoleService roleService) {
        this.roleService = roleService;
    }

    @ApiOperation(value = "view list of roles", response = Collection.class)
    @RequestMapping(method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public ResponseEntity<Collection<Role>> getList() {
        Collection<Role> list = roleService.findAll();
        return !list.isEmpty()
                ? new ResponseEntity<>(list, HttpStatus.OK)
                : new ResponseEntity(HttpStatus.NO_CONTENT);
    }

    @ApiOperation(value = "search role with name", response = Role.class) // todo same ulr like list
    @RequestMapping(params = "name", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public ResponseEntity<Role> getByName(@RequestParam String name) {
        Role role = roleService.findByName(name);
        return role != null
                ? new ResponseEntity<>(role, HttpStatus.OK)
                : new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @ApiOperation(value = "search role with ID", response = Role.class)
    @RequestMapping(value = "/{id}", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public ResponseEntity<Role> get(@PathVariable UUID id) {
        Role role = roleService.findById(id);
        return role != null
                ? new ResponseEntity<>(role, HttpStatus.OK)
                : new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @ApiOperation(value = "add role", response = Role.class)
    @RequestMapping(method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public ResponseEntity<Role> save(@RequestBody Role role) {
        roleService.save(role);
        return new ResponseEntity<>(role, HttpStatus.CREATED);
    }

    @ApiOperation(value = "update role")
    @RequestMapping(value = "/{id}", method = RequestMethod.PUT, produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public ResponseEntity<Void> update(@PathVariable UUID id, @RequestBody Role role) {
        Role entity = roleService.findById(id);
        if (entity == null) {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
        role.setId(entity.getId());
        roleService.save(role);
        return new ResponseEntity<>(HttpStatus.CREATED);
    }

    @ApiOperation(value = "delete role")
    @RequestMapping(value = "/{id}", method = RequestMethod.DELETE, produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public ResponseEntity<Void> delete(@PathVariable UUID id) {
        if (roleService.findById(id) == null) {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
        roleService.delete(id);
        return new ResponseEntity<>(HttpStatus.OK);
    }

}
