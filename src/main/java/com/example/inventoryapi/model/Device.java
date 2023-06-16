package com.example.inventoryapi.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import jakarta.persistence.*;

import java.sql.Date;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name="devices")
@JsonPropertyOrder({ "device_id", "name", "serial_number", "description", "eanDevice", "location", "quantity", "condition", "status", "date_added", "qr_code", "returned"})
public class Device {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO) @JsonProperty("device_id")
    private Long deviceId;
    @Column(name="name")
    private String name;

    @Column(name="serial_number") @JsonProperty("serial_number")
    private String serialNumber;

    @Column(name="description")
    private String description;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "eanDeviceId", nullable = false)
    private EanDevice eanDevice;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "locationId", nullable = false)
    private Location location;

    @Column(name="quantity", nullable = false)
    private int quantity;

    @Column(name="condition", nullable = false)
    private String condition;

    @Column(name="status", nullable = false)
    private String status;

    @Column(name="date_added", nullable = false) @JsonProperty("date_added")
    private Date dateAdded;

    @Column(name="qr_code", nullable = false) @JsonProperty("qr_code")
    private String qrCode;

    @Column(name="returned", nullable = false)
    private boolean returned;

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "device", cascade = {CascadeType.MERGE, CascadeType.PERSIST, CascadeType.REMOVE})
    private Set<DeviceHistory> deviceHistories = new HashSet<DeviceHistory>();


    public Device() {}

//    public Device(String name, String serialNumber, String description, EanDevice eanDevice, Location location,
//                  int quantity, String condition, String status, Date dateAdded, String qrCode) {
//        this.name = name;
//        this.serialNumber = serialNumber;
//        this.description = description;
//        this.eanDevice = eanDevice;
//        this.location = location;
//        this.quantity = quantity;
//        this.condition = condition;
//        this.status = status;
//        this.dateAdded = dateAdded;
//        this.qrCode = qrCode;
//    }


    public Long getDeviceId() {
        return deviceId;
    }

    public void setDeviceId(Long deviceId) {
        this.deviceId = deviceId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSerialNumber() {
        return serialNumber;
    }

    public void setSerialNumber(String serialNumber) {
        this.serialNumber = serialNumber;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public EanDevice getEanDevice() {
        return eanDevice;
    }

    public void setEanDevice(EanDevice eanDevice) {
        this.eanDevice = eanDevice;
    }

    public Location getLocation() {
        return location;
    }

    public void setLocation(Location location) {
        this.location = location;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public String getCondition() {
        return condition;
    }

    public void setCondition(String condition) {
        this.condition = condition;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public Date getDateAdded() {
        return dateAdded;
    }

    public void setDateAdded(Date dateAdded) {
        this.dateAdded = dateAdded;
    }

    public String getQrCode() {
        return qrCode;
    }

    public void setQrCode(String qrCode) {
        this.qrCode = qrCode;
    }

    public boolean isReturned() {
        return returned;
    }

    public void setReturned(boolean returned) {
        this.returned = returned;
    }
}
