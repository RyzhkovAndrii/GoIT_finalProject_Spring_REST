package com.ua.goit.gojava7.ryzhkov.finalproject.model;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.ua.goit.gojava7.ryzhkov.finalproject.jackson.deserializer.CustomUserDeserializer;
import com.ua.goit.gojava7.ryzhkov.finalproject.jackson.serializer.CustomUserSerializer;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
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
@ApiModel
public class User extends BaseEntity {

    @Column(name = "username", nullable = false)
    @ApiModelProperty(required = true, position = 1)
    private String username;

    @Column(name = "password", nullable = false)
    @ApiModelProperty(required = true, position = 2)
    private String password;

    @Transient
    @ApiModelProperty(hidden = true)
    private String confirmPassword; // todo confirm password

    @Temporal(TemporalType.DATE)
    @ApiModelProperty(readOnly = true, position = 3)
    @Column(name = "registration_date", nullable = false)
    private Date registrationDate;

    @ManyToMany
    @JoinTable(name = "users_roles",
            joinColumns = @JoinColumn(name = "user_id", referencedColumnName = "id"),
            inverseJoinColumns = @JoinColumn(name = "role_id", referencedColumnName = "id"))
    private Set<Role> roles;

    @OneToOne(mappedBy = "user")
    @ApiModelProperty(dataType = "java.lang.String", position = 4)
    private Employee employee;

    @ApiModelProperty(hidden = true)
    public Set<Role> getSetRoles() {
        return roles;
    }

    @ApiModelProperty(hidden = true)
    public List<Role> getRoles() {
        return getSetRoles() != null ? new ArrayList<>(getSetRoles()) : null;
    }

}
