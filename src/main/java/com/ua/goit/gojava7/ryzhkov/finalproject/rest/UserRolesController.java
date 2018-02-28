package com.ua.goit.gojava7.ryzhkov.finalproject.rest;

import com.ua.goit.gojava7.ryzhkov.finalproject.converter.ModelConversionService;
import com.ua.goit.gojava7.ryzhkov.finalproject.dto.RoleResponse;
import com.ua.goit.gojava7.ryzhkov.finalproject.service.UserService;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.Collection;
import java.util.UUID;

@RestController
@RequestMapping(value = "/users/{user}/roles")
@PreAuthorize("hasRole('ROLE_ADMIN')")
public class UserRolesController {

    private final UserService userService;

    private final ModelConversionService conversionService;

    @Autowired
    public UserRolesController(UserService userService, ModelConversionService conversionService) {
        this.userService = userService;
        this.conversionService = conversionService;
    }

    @ApiOperation(value = "view list of user's roles")
    @RequestMapping(method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    @ResponseStatus(HttpStatus.OK)
    public Collection<RoleResponse> getUserRoles(@PathVariable("user") UUID id) {
        return conversionService.convert(userService.findById(id).getRoles(), RoleResponse.class);
    }

    @ApiOperation(value = "add role to user")
    @RequestMapping(value = "/{role}", method = RequestMethod.PUT, produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    @ResponseStatus(HttpStatus.OK)
    public void addUserRole(@PathVariable("user") UUID userId, @PathVariable("role") UUID roleId) {
        userService.addRoleToUser(userId, roleId);
    }

    @ApiOperation(value = "delete role from user")
    @RequestMapping(value = "/{role}", method = RequestMethod.DELETE, produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    @ResponseStatus(HttpStatus.OK)
    public void deleteUserRole(@PathVariable("user") UUID userId, @PathVariable("role") UUID roleId) {
        userService.deleteRoleFromUser(userId, roleId);
    }

}
