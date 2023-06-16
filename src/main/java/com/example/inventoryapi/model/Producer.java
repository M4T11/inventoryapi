package com.example.inventoryapi.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import jakarta.persistence.*;

import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name="producers")
@JsonPropertyOrder({ "producer_id", "name"})
public class Producer {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO) @JsonProperty("producer_id")
    private Long producerId;
    @Column(name="name", nullable = false, unique = true)
    private String name;
    @OneToMany(fetch = FetchType.LAZY, mappedBy = "producer", cascade = {CascadeType.MERGE, CascadeType.PERSIST})
    private Set<EanDevice> eanDevices = new HashSet<EanDevice>();

    public Producer() {

    }
//    public Producer(String name) {
//        this.name = name;
//    }

    public Long getProducerId() {
        return producerId;
    }

    public void setProducerId(Long producerId) {
        this.producerId = producerId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
