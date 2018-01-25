package com.ua.goit.gojava7.ryzhkov.finalproject.jackson.serializer;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.databind.JsonSerializer;
import com.fasterxml.jackson.databind.SerializerProvider;
import com.ua.goit.gojava7.ryzhkov.finalproject.model.Employee;
import org.springframework.boot.jackson.JsonComponent;

import java.io.IOException;

@JsonComponent
public class CustomEmployeeSerializer extends JsonSerializer<Employee> {

    @Override
    public void serialize(
            Employee employee, JsonGenerator jsonGenerator, SerializerProvider serializerProvider)
            throws IOException {
        jsonGenerator.writeStartObject();
        jsonGenerator.writeStringField("id", String.valueOf(employee.getId()));
        jsonGenerator.writeStringField("email", employee.getEmail());
        jsonGenerator.writeStringField("first-name", employee.getFirstName());
        jsonGenerator.writeStringField("last-name", employee.getLastName());
        jsonGenerator.writeStringField("department", employee.getDepartment().getId().toString());
        jsonGenerator.writeStringField("post", employee.getPost().getId().toString());
        jsonGenerator.writeNumberField("hourly-rate", employee.getHourlyRate());
        jsonGenerator.writeStringField("status", employee.getStatus().getId().toString());
        if (employee.getUser() != null) {
            jsonGenerator.writeStringField("user", employee.getUser().getId().toString());
        } else {
            jsonGenerator.writeNullField("user");
        }
        jsonGenerator.writeNumberField("current-working-hours", employee.getCurrentWorkingHours());
        jsonGenerator.writeEndObject();
    }

}
