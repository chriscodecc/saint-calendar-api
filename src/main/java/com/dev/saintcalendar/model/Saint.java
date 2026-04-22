package com.dev.saintcalendar.model;

import jakarta.persistence.*;
import java.util.ArrayList;
import java.util.List;

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

    @Column(name = "is_martyr") 
    private boolean isMartyr;

    // UPDATE THE GETTER/SETTER names to be safe
    public boolean isMartyr() { return isMartyr; }
    public void setMartyr(boolean martyr) { isMartyr = martyr; }
    private String patronage;

    private String tropar;
    private String kondak;

    

    @Column(columnDefinition = "TEXT")
    private String description;

    @OneToMany(mappedBy = "saint", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Quote> quotes = new ArrayList<>();

    // The Helper Method
    public void addQuote(Quote quote) {
        quotes.add(quote);
        quote.setSaint(this);
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getDay() {
        return day;
    }

    public void setDay(int feastDay) {
        this.day = feastDay;
    }

    public int getMonth() {
        return month;
    }

    public void setMonth(int feastMonth) {
        this.month = feastMonth;
    }

    public String getPatronage() {
        return patronage;
    }

    public void setPatronage(String patronage) {
        this.patronage = patronage;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public List<Quote> getQuotes() {
        return quotes;
    }

    public void setQuotes(List<Quote> quotes) {
        this.quotes = quotes;
    }

    public String getTropar() {
        return tropar;
    }

    public void setTropar(String tropar) {
        this.tropar = tropar;
    }

    public String getKondak() {
        return kondak;
    }

    public void setKondak(String kondak) {
        this.kondak = kondak;
    }

    public boolean isIsMartyr() {
        return isMartyr;
    }

    public void setIsMartyr(boolean isMartyr) {
        this.isMartyr = isMartyr;
    }


  

   
}