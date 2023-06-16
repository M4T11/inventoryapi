package com.example.inventoryapi.service;


import com.example.inventoryapi.model.Device;
import com.example.inventoryapi.model.DeviceHistory;
import com.example.inventoryapi.repository.DeviceHistoryRepository;
import com.example.inventoryapi.repository.DeviceRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.sql.Timestamp;
import java.util.List;

import static org.springframework.http.HttpStatus.NOT_FOUND;

@Service
public class DeviceHistoryService {

    @Autowired
    DeviceHistoryRepository deviceHistoryRepository;

    @Autowired
    DeviceRepository deviceRepository;

    public List<DeviceHistory> getDevicesHistories() {
        return deviceHistoryRepository.findAll();
    }

    public List<DeviceHistory> getDeviceHistoriesByDeviceId(Long deviceId) {
        return deviceHistoryRepository.findAllByDeviceDeviceId(deviceId);
    }

    public DeviceHistory createDeviceHistory(Device device, String event) {
        DeviceHistory deviceHistory = new DeviceHistory();
        if(deviceRepository.existsById(device.getDeviceId())) {
            deviceHistory.setDevice(device);
        } else {
            throw new ResponseStatusException(NOT_FOUND, "Device not found!");
        }
        deviceHistory.setDate(new Timestamp(System.currentTimeMillis()));
        deviceHistory.setEvent(event);
        return deviceHistoryRepository.save(deviceHistory);
    }



}
