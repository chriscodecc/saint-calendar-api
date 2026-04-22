package com.dev.saintcalendar.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;

@Entity
public class Quote {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(columnDefinition = "TEXT")
    private String text;
    private String source;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "saint_id", nullable = false)
    @JsonIgnore
    private Saint saint;

    // Getters and Setters
    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }
    public String getText() { return text; }
    public void setText(String text) { this.text = text; }
    public String getSource() { return source; }
    public void setSource(String source) { this.source = source; }
    public Saint getSaint() { return saint; }
    public void setSaint(Saint saint) { this.saint = saint; }
}