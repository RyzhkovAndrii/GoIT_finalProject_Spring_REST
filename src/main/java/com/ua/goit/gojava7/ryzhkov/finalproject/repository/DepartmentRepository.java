package com.ua.goit.gojava7.ryzhkov.finalproject.repository;

import com.ua.goit.gojava7.ryzhkov.finalproject.model.Department;

import java.util.UUID;

public interface DepartmentRepository extends NamedEntityRepository<Department, UUID> {
}
