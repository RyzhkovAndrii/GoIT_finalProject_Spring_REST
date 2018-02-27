package com.ua.goit.gojava7.ryzhkov.finalproject.converter;

import com.ua.goit.gojava7.ryzhkov.finalproject.dto.EmployeeResponse;
import com.ua.goit.gojava7.ryzhkov.finalproject.model.Employee;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

@Component
public class EmployeeToEmployeeResponseConverter implements Converter<Employee, EmployeeResponse> {

    @Override
    public EmployeeResponse convert(Employee employee) {
        EmployeeResponse employeeResponse = new EmployeeResponse();
        employeeResponse.setId(employee.getId());
        employeeResponse.setEmail(employee.getEmail());
        employeeResponse.setFirstName(employee.getFirstName());
        employeeResponse.setLastName(employee.getLastName());
        employeeResponse.setDepartmentId(employee.getDepartment().getId());
        employeeResponse.setPostId(employee.getPost().getId());
        employeeResponse.setHourlyRate(employee.getHourlyRate());
        employeeResponse.setStatusId(employee.getStatus().getId());
        if (employee.getUser() != null) {
            employeeResponse.setUserId(employee.getUser().getId());
        }
        employeeResponse.setCurrentWorkingHours(employee.getCurrentWorkingHours());
        return employeeResponse;
    }

}
