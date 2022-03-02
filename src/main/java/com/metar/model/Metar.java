package com.metar.model;


import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.time.Instant;
import java.util.Optional;

@Entity
@Table(name = "metars")
public class Metar {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank
    @Size(max = 255)
    private String metarData;

    @NotNull
    private Instant dateTime;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "subscription_id", nullable = false)
    private Subscription subscription;

    public Metar() {
    }

    public Metar(String metarData, Instant dateTime, Subscription subscription) {
        this.metarData = metarData;
        this.dateTime = dateTime;
        this.subscription = subscription;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getMetarData() {
        return metarData;
    }

    public void setMetarData(String metarData) {
        this.metarData = metarData;
    }

    public Instant getDateTime() {
        return dateTime;
    }

    public void setDateTime(Instant dateTime) {
        this.dateTime = dateTime;
    }

    public Subscription getSubscription() {
        return subscription;
    }

    public void setSubscription(Subscription subscription) {
        this.subscription = subscription;
    }
}
