package com.example.inventoryapi.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import jakarta.persistence.*;

import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name="ean_devices")
@JsonPropertyOrder({ "ean_device_id", "ean", "category", "producer", "model"})
public class EanDevice {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO) @JsonProperty("ean_device_id")
    private Long eanDeviceId;
    @Column(name="ean", nullable = false, unique = true)
    private String ean;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "categoryId", nullable = false)
    private Category category;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "producerId", nullable = false)
    private Producer producer;

    @Column(name="model", nullable = false, unique = true)
    private String model;

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "eanDevice", cascade = {CascadeType.MERGE, CascadeType.PERSIST})
    private Set<Device> devices = new HashSet<Device>();

    public EanDevice() {

    }

//    public EanDevice(String ean, Category category, Producer producer, String model) {
//        this.ean = ean;
//        this.category = category;
//        this.producer = producer;
//        this.model = model;
//    }

    public Long getEanDeviceId() {
        return eanDeviceId;
    }

    public void setEanDeviceId(Long eanDeviceId) {
        this.eanDeviceId = eanDeviceId;
    }

    public String getEan() {
        return ean;
    }

    public void setEan(String ean) {
        this.ean = ean;
    }

    public Category getCategory() {
        return category;
    }

    public void setCategory(Category category) {
        this.category = category;
    }

    public Producer getProducer() {
        return producer;
    }

    public void setProducer(Producer producer) {
        this.producer = producer;
    }

    public String getModel() {
        return model;
    }

    public void setModel(String model) {
        this.model = model;
    }
}
