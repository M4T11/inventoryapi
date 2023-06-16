package com.example.inventoryapi.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import jakarta.persistence.*;

import java.sql.Timestamp;

@Entity
@Table(name="device_histories")
@JsonPropertyOrder({ "history_id", "event", "device", "date"})
public class DeviceHistory {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO) @JsonProperty("history_id")
    private Long historyId;

    @Column(name="event", nullable = false)
    private String event;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "deviceId", nullable = false)
    private Device device;

    @Column(name="date", nullable = false)
    private Timestamp date;

    public DeviceHistory() {}

//    public DeviceHistory(String event, Device device) {
//        this.event = event;
//        this.device = device;
//
//    }


    public Long getHistoryId() {
        return historyId;
    }

    public void setHistoryId(Long historyId) {
        this.historyId = historyId;
    }

    public String getEvent() {
        return event;
    }

    public void setEvent(String event) {
        this.event = event;
    }

    public Device getDevice() {
        return device;
    }

    public void setDevice(Device device) {
        this.device = device;
    }

    public Timestamp getDate() {
        return date;
    }

    public void setDate(Timestamp date) {
        this.date = date;
    }
}
