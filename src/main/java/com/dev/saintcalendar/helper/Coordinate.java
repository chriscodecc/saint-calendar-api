package com.dev.saintcalendar.helper;

import jakarta.persistence.Embeddable;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@Embeddable
public class Coordinate {
    private Double x;
    private Double y;

    public Coordinate(double x, double y) {
        this.x = x;
        this.y = y;
    }
}
