package com.ua.goit.gojava7.ryzhkov.finalproject.model;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.ua.goit.gojava7.ryzhkov.finalproject.util.deserializer.CustomUserDeserializer;
import com.ua.goit.gojava7.ryzhkov.finalproject.util.serializer.CustomUserSerializer;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Set;

@Entity
@Table(name = "users")
@Getter
@Setter
@RequiredArgsConstructor
@ToString
@JsonSerialize(using = CustomUserSerializer.class)
@JsonDeserialize(using = CustomUserDeserializer.class)
public class User extends BaseEntity {

    @Column(name = "username", nullable = false)
    private String username;

    @Column(name = "password", nullable = false)
    private String password;

    @Transient
    private String confirmPassword;

    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "registration_date", nullable = false)
    private Date registrationDate;

    @ManyToMany
    @JoinTable(name = "users_roles",
            joinColumns = @JoinColumn(name = "user_id", referencedColumnName = "id"),
            inverseJoinColumns = @JoinColumn(name = "role_id", referencedColumnName = "id"))
    private Set<Role> roles;

    @OneToOne(mappedBy = "user")
    private Employee employee;

    public Set<Role> getSetRoles() {
        return roles;
    }

    public List<Role> getRoles() {
        return getSetRoles() != null ? new ArrayList<>(getSetRoles()) : null;
    }

}
