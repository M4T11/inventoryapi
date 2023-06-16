package com.example.inventoryapi.controller;

import com.example.inventoryapi.model.Device;
import com.example.inventoryapi.service.DeviceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class DeviceController {

    @Autowired
    DeviceService deviceService;

    @RequestMapping(value="/devices", method= RequestMethod.GET)
    public List<Device> getDevices () {
        return deviceService.getDevices();
    }

    @RequestMapping(value="/devices/id/{device_id}", method= RequestMethod.GET)
    public Device getDeviceById (@PathVariable(value = "device_id") Long deviceId) {
        return deviceService.getDeviceById(deviceId);
    }

    @RequestMapping(value="/devices/qr/{qr_code}", method= RequestMethod.GET)
    public Device getDeviceByQrCode (@PathVariable(value = "qr_code") String qrCode) {
        return deviceService.getDeviceByQrCode(qrCode);
    }

    @RequestMapping(value="/devices/sn/{sn}", method= RequestMethod.GET)
    public Device getDeviceBySerialNumber (@PathVariable(value = "sn") String serialNumber) {
        return deviceService.getDeviceBySerialNumber(serialNumber);
    }

    @RequestMapping(value="/devices", method= RequestMethod.POST)
    public Device createDevice (@RequestBody Device device) {
        return deviceService.createDevice(device);
    }

    @RequestMapping(value="/devices/id/{device_id}", method= RequestMethod.PUT)
    public Device updateDevice (@PathVariable(value = "device_id") Long deviceId, @RequestBody Device device) {
        return deviceService.updateDevice(device, deviceId);
    }

    @RequestMapping(value="/devices/id/{device_id}", method= RequestMethod.DELETE)
    public void deleteDevice (@PathVariable(value = "device_id") Long deviceId, @RequestBody Device device) {
        deviceService.deleteDevice(deviceId);
    }



}
