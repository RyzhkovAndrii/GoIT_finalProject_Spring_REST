package com.ua.goit.gojava7.ryzhkov.finalproject.model;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.Entity;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import java.util.Set;

@Entity
@Table(name = "employee_statuses")
@RequiredArgsConstructor
@Getter
@Setter
@ToString
public class EmployeeStatus extends NamedEntity {

    @OneToMany(mappedBy = "status")
    private Set<Employee> employees;

}
