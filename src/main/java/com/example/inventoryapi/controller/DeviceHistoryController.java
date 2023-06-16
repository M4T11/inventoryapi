package com.example.inventoryapi.controller;

import com.example.inventoryapi.model.Device;
import com.example.inventoryapi.model.DeviceHistory;
import com.example.inventoryapi.service.DeviceHistoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class DeviceHistoryController {

    @Autowired
    DeviceHistoryService deviceHistoryService;

    @RequestMapping(value="/deviceshistories", method= RequestMethod.GET)
    public List<DeviceHistory> getDevicesHistories () {
        return deviceHistoryService.getDevicesHistories();
    }

    @RequestMapping(value="/deviceshistories/id/{device_id}", method= RequestMethod.GET)
    public List<DeviceHistory> getDevicesHistories (@PathVariable(value = "device_id") Long deviceId) {
        return deviceHistoryService.getDeviceHistoriesByDeviceId(deviceId);
    }

}
