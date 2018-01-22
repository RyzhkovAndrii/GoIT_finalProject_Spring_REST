package com.ua.goit.gojava7.ryzhkov.finalproject.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.Entity;
import javax.persistence.ManyToMany;
import javax.persistence.Table;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;

@Entity
@Table(name = "roles")
@RequiredArgsConstructor
@Setter
@ToString
public class Role extends NamedEntity {

    @ManyToMany(mappedBy = "roles")
    private Set<User> users;

    @JsonIgnore
    public Set<User> getSetUsers() {
        return users;
    }

    @JsonIgnore
    public List<User> getUsers() {
        return getSetUsers() != null ? new ArrayList<>(getSetUsers()) : null;
    }

}
