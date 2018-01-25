package com.ua.goit.gojava7.ryzhkov.finalproject.model;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.ua.goit.gojava7.ryzhkov.finalproject.jackson.deserializer.CustomEmployeeDeserializer;
import com.ua.goit.gojava7.ryzhkov.finalproject.jackson.serializer.CustomEmployeeSerializer;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.*;
import java.util.Set;

@Entity
@Table(name = "employees")
@RequiredArgsConstructor
@Getter
@Setter
@ToString
@JsonSerialize(using = CustomEmployeeSerializer.class)
@JsonDeserialize(using = CustomEmployeeDeserializer.class)
@ApiModel
public class Employee extends BaseEntity {

    @Column(name = "email", nullable = false, unique = true)
    @ApiModelProperty(required = true, position = 1)
    private String email;

    @Column(name = "first_name", nullable = false)
    @ApiModelProperty(required = true, position = 2)
    private String firstName;

    @ApiModelProperty(required = true, position = 3)
    @Column(name = "last_name", nullable = false)
    private String lastName;

    @ManyToOne
    @JoinColumn(name = "department_id", nullable = false)
    @ApiModelProperty(required = true, position = 4, dataType = "java.lang.String")
    private Department department;

    @ManyToOne
    @JoinColumn(name = "post_id", nullable = false)
    @ApiModelProperty(required = true, position = 5, dataType = "java.lang.String")
    private EmployeePost post;

    @Column(name = "hourly_rate", nullable = false)
    @ApiModelProperty(required = true, position = 6)
    private double hourlyRate;

    @ManyToOne
    @JoinColumn(name = "status_id", nullable = false)
    @ApiModelProperty(required = true, position = 7, dataType = "java.lang.String")
    private EmployeeStatus status;

    @OneToMany(mappedBy = "employee")
    @ApiModelProperty(hidden = true)
    private Set<Payment> payments;

    @ManyToMany(mappedBy = "employees")
    @ApiModelProperty(hidden = true)
    private Set<Event> events;

    @OneToOne
    @JoinColumn(name = "user_id", unique = true)
    @ApiModelProperty(position = 8, dataType = "java.lang.String")
    private User user;

    @Column(name = "current_working_hours", nullable = false)
    @ApiModelProperty( position = 9, readOnly = true)
    private double currentWorkingHours;

}
