package com.ua.goit.gojava7.ryzhkov.finalproject.converter;

import com.ua.goit.gojava7.ryzhkov.finalproject.model.Role;
import com.ua.goit.gojava7.ryzhkov.finalproject.dto.RoleResponse;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

@Component
public class RoleToRoleResponseConverter implements Converter<Role, RoleResponse> {

    @Override
    public RoleResponse convert(Role role) {
        RoleResponse roleResponse = new RoleResponse();
        roleResponse.setId(role.getId());
        roleResponse.setName(role.getName());
        return roleResponse;
    }

}
