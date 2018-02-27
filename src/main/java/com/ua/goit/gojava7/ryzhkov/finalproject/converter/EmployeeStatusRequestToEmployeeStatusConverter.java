package com.ua.goit.gojava7.ryzhkov.finalproject.converter;

import com.ua.goit.gojava7.ryzhkov.finalproject.dto.EmployeeStatusRequest;
import com.ua.goit.gojava7.ryzhkov.finalproject.model.EmployeeStatus;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

@Component
public class EmployeeStatusRequestToEmployeeStatusConverter
        implements Converter<EmployeeStatusRequest, EmployeeStatus> {

    @Override
    public EmployeeStatus convert(EmployeeStatusRequest request) {
        EmployeeStatus entity = new EmployeeStatus();
        entity.setName(request.getName());
        return entity;
    }

}
