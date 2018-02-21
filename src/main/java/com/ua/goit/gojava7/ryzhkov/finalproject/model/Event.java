package com.ua.goit.gojava7.ryzhkov.finalproject.model;

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
public class Event extends BaseEntity {

    @Column(name = "description")
    private String description;

    @Temporal(TemporalType.DATE)
    @Column(name = "date", nullable = false)
    private Date date;

    @Column(name = "duration", nullable = false)
    private double duration;

    @ManyToOne
    @JoinColumn(name = "event_type_id", nullable = false)
    private EventType type;

    @ManyToMany
    @JoinTable(name = "events_employees",
            joinColumns = @JoinColumn(name = "event_id", referencedColumnName = "id"),
            inverseJoinColumns = @JoinColumn(name = "employee_id", referencedColumnName = "id"))
    private Set<Employee> employees;

}
