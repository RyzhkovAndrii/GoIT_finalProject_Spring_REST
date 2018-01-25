package com.ua.goit.gojava7.ryzhkov.finalproject.jackson.serializer;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.databind.JsonSerializer;
import com.fasterxml.jackson.databind.SerializerProvider;
import com.ua.goit.gojava7.ryzhkov.finalproject.model.User;

import java.io.IOException;

public class CustomUserSerializer extends JsonSerializer<User> {

    @Override
    public void serialize(
            User user, JsonGenerator jsonGenerator, SerializerProvider serializerProvider)
            throws IOException {
        jsonGenerator.writeStartObject();
        jsonGenerator.writeStringField("id", String.valueOf(user.getId()));
        jsonGenerator.writeStringField("username", user.getUsername());
        jsonGenerator.writeStringField("password", user.getPassword());
        jsonGenerator.writeStringField("registration-date", String.valueOf(user.getRegistrationDate()));
        if (user.getEmployee() != null) {
            jsonGenerator.writeStringField("employee", user.getEmployee().getId().toString());
        } else {
            jsonGenerator.writeNullField("employee");
        }
        jsonGenerator.writeEndObject();
    }

}
