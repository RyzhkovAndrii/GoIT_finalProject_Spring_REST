package com.ua.goit.gojava7.ryzhkov.finalproject.converter;

import com.ua.goit.gojava7.ryzhkov.finalproject.dto.EmployeeRequest;
import com.ua.goit.gojava7.ryzhkov.finalproject.model.Employee;
import com.ua.goit.gojava7.ryzhkov.finalproject.repository.DepartmentRepository;
import com.ua.goit.gojava7.ryzhkov.finalproject.repository.EmployeePostRepository;
import com.ua.goit.gojava7.ryzhkov.finalproject.repository.EmployeeStatusRepository;
import com.ua.goit.gojava7.ryzhkov.finalproject.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

@Component
public class EmployeeRequestToEmployeeConverter implements Converter<EmployeeRequest, Employee> {

    @Autowired
    private DepartmentRepository departmentRepository;

    @Autowired
    private EmployeePostRepository employeePostRepository;

    @Autowired
    private EmployeeStatusRepository employeeStatusRepository;

    @Autowired
    private UserRepository userRepository;

    @Override
    public Employee convert(EmployeeRequest employeeRequest) {
        Employee employee = new Employee();
        employee.setEmail(employeeRequest.getEmail());
        employee.setFirstName(employeeRequest.getFirstName());
        employee.setLastName(employeeRequest.getLastName());
        employee.setLastName(employeeRequest.getLastName());
        employee.setDepartment(departmentRepository.findOne(employeeRequest.getDepartmentId()));
        employee.setPost(employeePostRepository.findOne(employeeRequest.getPostId()));
        employee.setHourlyRate(employeeRequest.getHourlyRate());
        employee.setStatus(employeeStatusRepository.findOne(employeeRequest.getStatusId()));
        if (employeeRequest.getUserId() != null) {
            employee.setUser(userRepository.findOne(employeeRequest.getUserId()));
        }
        return employee;
    }

}
