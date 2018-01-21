package com.ua.goit.gojava7.ryzhkov.finalproject.repository;

import com.ua.goit.gojava7.ryzhkov.finalproject.model.EmployeePost;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface EmployeePostRepository extends NamedEntityRepository<EmployeePost, UUID> {
}
