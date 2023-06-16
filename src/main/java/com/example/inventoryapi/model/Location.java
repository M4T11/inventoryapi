package com.example.inventoryapi.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import jakarta.persistence.*;

import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name="locations")
@JsonPropertyOrder({ "location_id", "name"})
public class Location {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO) @JsonProperty("location_id")
    private Long locationId;
    @Column(name="name", nullable = false, unique = true)
    private String name;
    @OneToMany(fetch = FetchType.LAZY, mappedBy = "location", cascade = {CascadeType.MERGE, CascadeType.PERSIST})
    private Set<Device> devices = new HashSet<Device>();

    public Location() {

    }
//    public Location(String name) {
//        this.name = name;
//    }

    public Long getLocationId() {
        return locationId;
    }

    public void setLocationId(Long locationId) {
        this.locationId = locationId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
