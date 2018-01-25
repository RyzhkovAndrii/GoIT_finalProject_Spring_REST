package com.ua.goit.gojava7.ryzhkov.finalproject.jackson.deserializer;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JsonDeserializer;
import com.fasterxml.jackson.databind.JsonNode;
import com.ua.goit.gojava7.ryzhkov.finalproject.model.Employee;
import com.ua.goit.gojava7.ryzhkov.finalproject.model.User;
import com.ua.goit.gojava7.ryzhkov.finalproject.repository.EmployeeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.jackson.JsonComponent;

import java.io.IOException;
import java.util.UUID;

@JsonComponent
public class CustomUserDeserializer extends JsonDeserializer<User> {

    private EmployeeRepository employeeRepository;

    @Autowired
    public CustomUserDeserializer(EmployeeRepository employeeRepository) {
        this.employeeRepository = employeeRepository;
    }

    @Override
    public User deserialize(JsonParser jsonParser, DeserializationContext deserializationContext)
            throws IOException {
        JsonNode node = jsonParser.getCodec().readTree(jsonParser);
        User user = new User();
        user.setUsername(node.get("username").asText());
        user.setPassword(node.get("password").asText());
        if (node.get("employee") != null) {
            UUID employeeId = UUID.fromString(node.get("employee").asText());
            Employee employee = employeeRepository.findOne(employeeId);
            if (employee != null) {
                user.setEmployee(employee);
            }
        }
        return user;
    }

}
