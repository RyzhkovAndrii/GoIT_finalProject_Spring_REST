package com.ua.goit.gojava7.ryzhkov.finalproject.jackson.deserializer;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JsonDeserializer;
import com.fasterxml.jackson.databind.JsonNode;
import com.ua.goit.gojava7.ryzhkov.finalproject.model.Employee;
import com.ua.goit.gojava7.ryzhkov.finalproject.repository.DepartmentRepository;
import com.ua.goit.gojava7.ryzhkov.finalproject.repository.EmployeePostRepository;
import com.ua.goit.gojava7.ryzhkov.finalproject.repository.EmployeeStatusRepository;
import com.ua.goit.gojava7.ryzhkov.finalproject.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.jackson.JsonComponent;

import java.io.IOException;
import java.util.UUID;

@JsonComponent
public class CustomEmployeeDeserializer extends JsonDeserializer<Employee> {

    private DepartmentRepository departmentRepository;

    private EmployeePostRepository employeePostRepository;

    private EmployeeStatusRepository employeeStatusRepository;

    private UserRepository userRepository;

    @Autowired
    public CustomEmployeeDeserializer(DepartmentRepository departmentRepository,
                                      EmployeePostRepository employeePostRepository,
                                      EmployeeStatusRepository employeeStatusRepository,
                                      UserRepository userRepository) {
        this.departmentRepository = departmentRepository;
        this.employeePostRepository = employeePostRepository;
        this.employeeStatusRepository = employeeStatusRepository;
        this.userRepository = userRepository;
    }

    @Override
    public Employee deserialize(JsonParser jsonParser, DeserializationContext deserializationContext)
            throws IOException {
        JsonNode node = jsonParser.getCodec().readTree(jsonParser);
        Employee employee = new Employee();
        String email = node.get("email").asText();
        String firstName = node.get("first-name").asText();
        String lastName = node.get("last-name").asText();
        UUID departmentId = UUID.fromString(node.get("department").asText());
        UUID postId = UUID.fromString(node.get("post").asText());
        double hourlyRate = node.get("hourly-rate").asDouble(0);
        UUID statusId = UUID.fromString(node.get("status").asText());
        employee.setEmail(email);
        employee.setFirstName(firstName);
        employee.setLastName(lastName);
        employee.setDepartment(departmentRepository.findOne(departmentId));
        employee.setPost(employeePostRepository.findOne(postId));
        employee.setHourlyRate(hourlyRate);
        employee.setStatus(employeeStatusRepository.findOne(statusId));
        if (node.get("user") != null) {
            UUID userId = UUID.fromString(node.get("user").asText());
            employee.setUser(userRepository.findOne(userId));
        }
        return employee;
    }

}
