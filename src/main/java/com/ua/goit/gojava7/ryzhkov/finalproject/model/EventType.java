package com.ua.goit.gojava7.ryzhkov.finalproject.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import java.util.Set;

@Entity
@Table(name = "event_types")
@RequiredArgsConstructor
@Getter
@Setter
@ToString
public class EventType extends NamedEntity {

    @Column(name = "hourly_rate_coefficient", nullable = false)
    private double hourlyRateCoefficient;

    @OneToMany(mappedBy = "type")
    @JsonIgnore
    private Set<Event> events;

}
