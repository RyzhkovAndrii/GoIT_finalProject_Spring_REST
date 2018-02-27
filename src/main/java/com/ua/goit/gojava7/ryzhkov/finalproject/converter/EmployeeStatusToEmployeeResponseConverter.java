package com.ua.goit.gojava7.ryzhkov.finalproject.converter;

import com.ua.goit.gojava7.ryzhkov.finalproject.dto.EmployeeStatusResponse;
import com.ua.goit.gojava7.ryzhkov.finalproject.model.EmployeeStatus;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

@Component
public class EmployeeStatusToEmployeeResponseConverter
        implements Converter<EmployeeStatus, EmployeeStatusResponse> {

    @Override
    public EmployeeStatusResponse convert(EmployeeStatus employeeStatus) {
        EmployeeStatusResponse employeeStatusResponse = new EmployeeStatusResponse();
        employeeStatusResponse.setId(employeeStatus.getId());
        employeeStatusResponse.setName(employeeStatus.getName());
        return employeeStatusResponse;
    }

}
