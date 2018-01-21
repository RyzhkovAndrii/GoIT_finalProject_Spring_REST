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
@Table(name = "departments")
@RequiredArgsConstructor
@Getter
@Setter
@ToString
public class Department extends NamedEntity {

    @OneToMany(mappedBy = "department")
    private Set<Employee> employees;

}
