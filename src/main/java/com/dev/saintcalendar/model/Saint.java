package com.dev.saintcalendar.model;

import jakarta.persistence.*;
import java.util.ArrayList;
import java.util.List;

import com.dev.saintcalendar.helper.Coordinate;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.Setter;


@Getter
@Setter
@Entity
public class Saint {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;
    private int day;
    private int month;

    @JsonProperty("is_martyr")
    @Column(name = "is_martyr") 
    private boolean martyr;

    @Column(columnDefinition = "TEXT")
    private String patronage;

    @Column(columnDefinition = "TEXT")
    private String tropar;

    @Column(columnDefinition = "TEXT")  
    private String kondak;

    @Column(columnDefinition = "TEXT")
    private String description;

    @OneToMany(mappedBy = "saint", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Quote> quotes = new ArrayList<>();

    @OneToMany(mappedBy = "saint", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Relic> relics = new ArrayList<>();

    //Helper Method
    public void addQuote(Quote quote) {
        quotes.add(quote);
        quote.setSaint(this);
    }

    @ElementCollection
    @CollectionTable(name = "saint_links", joinColumns = @JoinColumn(name = "saint_id"))
    @Column(name = "link_url")
    private List<String> link_url; 

    private String imageLink;
   
}