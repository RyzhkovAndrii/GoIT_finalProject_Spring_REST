package com.ua.goit.gojava7.ryzhkov.finalproject.model;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.ua.goit.gojava7.ryzhkov.finalproject.jackson.deserializer.CustomEventDeserializer;
import com.ua.goit.gojava7.ryzhkov.finalproject.jackson.serializer.CustomEventSerializer;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.*;
import java.util.Date;
import java.util.Set;

@Entity
@Table(name = "events")
@RequiredArgsConstructor
@Getter
@Setter
@ToString
@JsonSerialize(using = CustomEventSerializer.class)
@JsonDeserialize(using = CustomEventDeserializer.class)
@ApiModel
public class Event extends BaseEntity {

    @Column(name = "description")
    @ApiModelProperty(position = 1)
    private String description;

    @Temporal(TemporalType.DATE)
    @ApiModelProperty(required = true, position = 2, example = "yyyy-MM-dd")
    @Column(name = "date", nullable = false)
    private Date date;

    @Column(name = "duration", nullable = false)
    @ApiModelProperty(required = true, position = 3)
    private double duration;

    @ManyToOne
    @JoinColumn(name = "event_type_id", nullable = false)
    @ApiModelProperty(required = true, position = 4, dataType = "java.lang.String")
    private EventType type;

    @ManyToMany
    @JoinTable(name = "events_employees",
            joinColumns = @JoinColumn(name = "event_id", referencedColumnName = "id"),
            inverseJoinColumns = @JoinColumn(name = "employee_id", referencedColumnName = "id"))
    @ApiModelProperty(hidden = true)
    private Set<Employee> employees;

}
