package com.ua.goit.gojava7.ryzhkov.finalproject.model;

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
public class Employee extends BaseEntity {

    @Column(name = "email", nullable = false, unique = true)
    private String email;

    @Column(name = "first_name", nullable = false)
    private String firstName;

    @Column(name = "last_name", nullable = false)
    private String lastName;

    @ManyToOne
    @JoinColumn(name = "department_id", nullable = false)
    private Department department;

    @ManyToOne
    @JoinColumn(name = "post_id", nullable = false)
    private EmployeePost post;

    @Column(name = "hourly_rate", nullable = false)
    private double hourlyRate;

    @ManyToOne
    @JoinColumn(name = "status_id", nullable = false)
    private EmployeeStatus status;

    @OneToMany(mappedBy = "employee")
    private Set<Payment> payments;

    @ManyToMany(mappedBy = "employees")
    private Set<Event> events;

    @OneToOne
    @JoinColumn(name = "user_id", unique = true)
    private User user;

    @Column(name = "current_working_hours", nullable = false)
    private double currentWorkingHours;

}
