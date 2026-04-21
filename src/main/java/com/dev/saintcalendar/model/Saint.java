package com.dev.saintcalendar.model;

import jakarta.persistence.Id;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;

@Entity
public class Saint {

    public Saint(){
        
    }
    
    public Saint(String name, int day, int month, String patronage, String description, String tropar, String kondakt, Boolean isMartyr){
        this.name = name;
        this.day = day;
        this.month = month;
        this.patronage = patronage;
        this.description = description;
        this.tropar = tropar;
        this.kondak = kondakt;
        this.isMartyr = isMartyr;
    }  
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id; 

    @Column
    private String name; 

    @Column(name = "feast_day")
    private int day;

    @Column(name = "feast_month")
    private int month;

    @Column
    private String patronage;

    @Column(columnDefinition = "TEXT")
    private String description;

    @Column(columnDefinition = "TEXT")
    private String tropar;

    @Column(columnDefinition = "TEXT")
    private String kondak; 

    @Column
    private boolean isMartyr;

    public long getId() {
        return id;
    }

    public void setId(long id) {
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

    public void setDay(int day) {
        this.day = day;
    }

    public int getMonth() {
        return month;
    }

    public void setMonth(int month) {
        this.month = month;
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

    public boolean getIsMartyr() {
        return isMartyr;
    }

    public void setIsMartyr(boolean isMartyr) {
        this.isMartyr = isMartyr;
    }
}
