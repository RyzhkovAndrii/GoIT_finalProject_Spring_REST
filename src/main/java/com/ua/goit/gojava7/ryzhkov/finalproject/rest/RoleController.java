package com.ua.goit.gojava7.ryzhkov.finalproject.rest;

import com.ua.goit.gojava7.ryzhkov.finalproject.model.Role;
import com.ua.goit.gojava7.ryzhkov.finalproject.model.dto.RoleDto;
import com.ua.goit.gojava7.ryzhkov.finalproject.model.dto.RoleSaveDto;
import com.ua.goit.gojava7.ryzhkov.finalproject.service.RoleService;
import io.swagger.annotations.ApiOperation;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.Collection;
import java.util.UUID;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/roles")
@PreAuthorize("hasRole('ROLE_ADMIN')")
public class RoleController {

    private RoleService roleService;

    private ModelMapper modelMapper;

    @Autowired
    public RoleController(RoleService roleService, ModelMapper modelMapper) {
        this.roleService = roleService;
        this.modelMapper = modelMapper;
    }

    @ApiOperation(value = "view list of roles", response = Collection.class)
    @RequestMapping(method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    @ResponseStatus(HttpStatus.OK)
    @ResponseBody
    public Collection<RoleDto> getList() {
        return convertToDto(roleService.findAll());
    }

    @ApiOperation(value = "search role with name") // todo same ulr like list
    @RequestMapping(params = "name", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    @ResponseStatus(HttpStatus.OK)
    @ResponseBody
    public RoleDto getByName(@RequestParam String name) {
        return convertToDto(roleService.findByName(name));
    }

    @ApiOperation(value = "search role with ID")
    @RequestMapping(value = "/{id}", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    @ResponseStatus(HttpStatus.OK)
    @ResponseBody
    public RoleDto get(@PathVariable UUID id) {
        return convertToDto(roleService.findById(id));
    }

    @ApiOperation(value = "add role")
    @RequestMapping(method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    @ResponseStatus(HttpStatus.CREATED)
    @ResponseBody
    public RoleDto save(@RequestBody RoleSaveDto roleDto) {
        return convertToDto(roleService.save(convertToEntity(roleDto)));
    }

    @ApiOperation(value = "update role")
    @RequestMapping(value = "/{id}", method = RequestMethod.PUT, produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    @ResponseStatus(HttpStatus.OK)
    public void update(@PathVariable UUID id, @RequestBody RoleSaveDto roleDto) {
        Role role = convertToEntity(roleDto);
        role.setId(id);
        roleService.update(role);
    }

    @ApiOperation(value = "delete role")
    @RequestMapping(value = "/{id}", method = RequestMethod.DELETE, produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    @ResponseStatus(HttpStatus.OK)
    public void delete(@PathVariable UUID id) {
        roleService.delete(id);
    }

    private Role convertToEntity(RoleSaveDto dto) {
        return modelMapper.map(dto, Role.class);
    }

    private RoleDto convertToDto(Role role) {
        return modelMapper.map(role, RoleDto.class);
    }

    private Collection<RoleDto> convertToDto(Collection<Role> roles) {
        return roles.stream().map(this::convertToDto).collect(Collectors.toList());
    }

}
