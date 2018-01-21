package com.ua.goit.gojava7.ryzhkov.finalproject.repository;

import com.ua.goit.gojava7.ryzhkov.finalproject.model.Role;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface RoleRepository extends NamedEntityRepository<Role, UUID> {
}
