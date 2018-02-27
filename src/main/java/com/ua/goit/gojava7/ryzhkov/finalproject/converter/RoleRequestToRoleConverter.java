package com.ua.goit.gojava7.ryzhkov.finalproject.converter;

import com.ua.goit.gojava7.ryzhkov.finalproject.dto.RoleRequest;
import com.ua.goit.gojava7.ryzhkov.finalproject.model.Role;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

@Component
public class RoleRequestToRoleConverter implements Converter<RoleRequest, Role> {

    @Override
    public Role convert(RoleRequest roleRequest) {
        Role role = new Role();
        role.setName(roleRequest.getName());
        return role;
    }

}
