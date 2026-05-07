package com.dev.saintcalendar.model;

import com.dev.saintcalendar.helper.Coordinate;
import com.fasterxml.jackson.annotation.JsonIgnore;

import jakarta.persistence.Column;
import jakarta.persistence.ElementCollection;
import jakarta.persistence.Embedded;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
public class Relic {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private String type;
    private String location;
    private boolean isVerified;
    
    @Embedded
    private Coordinate coordinate;

    @Column(columnDefinition = "TEXT")
    private String description; 

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "saint_id", nullable = false)
    @JsonIgnore
    private Saint saint;
}
