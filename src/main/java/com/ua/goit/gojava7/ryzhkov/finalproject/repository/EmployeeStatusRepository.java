package com.ua.goit.gojava7.ryzhkov.finalproject.repository;

import com.ua.goit.gojava7.ryzhkov.finalproject.model.EmployeeStatus;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface EmployeeStatusRepository extends NamedEntityRepository<EmployeeStatus, UUID> {
}
