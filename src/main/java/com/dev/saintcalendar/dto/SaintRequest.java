package com.dev.saintcalendar.dto;

import jakarta.validation.constraints.Min;

import java.util.List;

import com.dev.saintcalendar.model.Relic;

import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.NotBlank;

public record SaintRequest(
    @NotBlank(message = "Name is required")
    String name,

    @Min(1) 
    @Max(31)
    int day,

    @Min(1) 
    @Max(12)
    int month,
    
    String patronage,
    String description,
    String tropar,
    String kondak,
    boolean isMartyr,
    List<QuoteRequest> quotes,
    List<Relic> relics,
    String imageLink,
    List<String> link_url
) { } 
