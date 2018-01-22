package com.ua.goit.gojava7.ryzhkov.finalproject.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.*;
import java.util.Date;

@Entity
@Table(name = "payments")
@RequiredArgsConstructor
@Getter
@Setter
@ToString
public class Payment extends BaseEntity {

    @Temporal(TemporalType.DATE)
    @Column(name = "date")
    private Date date;

    @Column(name = "working_hours")
    private double workingHours;

    @Column(name = "salary")
    private double salary;

    @ManyToOne
    @JoinColumn(name = "employee_id")
    @JsonIgnore
    private Employee employee;

}
