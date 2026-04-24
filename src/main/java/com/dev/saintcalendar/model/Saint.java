package com.dev.saintcalendar.model;

import jakarta.persistence.*;
import java.util.ArrayList;
import java.util.List;

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
    @Column(name = "day") 
    private int day;

    @Column(name = "month") 
    private int month;

    @JsonProperty("is_martyr")
    @Column(name = "is_martyr") 
    private boolean martyr;

    private String patronage;

    private String tropar;
    private String kondak;

    @Column(columnDefinition = "TEXT")
    private String description;

    @OneToMany(mappedBy = "saint", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Quote> quotes = new ArrayList<>();

    //Helper Method
    public void addQuote(Quote quote) {
        quotes.add(quote);
        quote.setSaint(this);
    }

   
}