package com.metar.model;

import org.hibernate.annotations.NaturalId;

import org.hibernate.annotations.NaturalId;
import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;
import java.util.HashSet;
import java.util.Set;

import javax.persistence.*;

@Entity
@Table(name = "subscriptions", uniqueConstraints = {
        @UniqueConstraint(columnNames = {
                "icaoCode"
        })
})
public class Subscription {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NaturalId
    @NotBlank
    @Size(max = 4)
    private String icaoCode;

    @Size(max = 100)
    private String airportName;

    public Subscription() {

    }

    public Subscription(String icaoCode, String airportName) {
        this.icaoCode = icaoCode;
        this.airportName = airportName;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getIcaoCode() {
        return icaoCode;
    }

    public void setIcaoCode(String icaoCode) {
        this.icaoCode = icaoCode;
    }

    public String getAirportName() {
        return airportName;
    }

    public void setAirportName(String airportName) {
        this.airportName = airportName;
    }
}
