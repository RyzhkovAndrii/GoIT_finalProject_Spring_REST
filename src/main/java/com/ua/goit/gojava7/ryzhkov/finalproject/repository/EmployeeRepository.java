package com.ua.goit.gojava7.ryzhkov.finalproject.repository;

import com.ua.goit.gojava7.ryzhkov.finalproject.model.Employee;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface EmployeeRepository extends JpaRepository<Employee, UUID> {
}
