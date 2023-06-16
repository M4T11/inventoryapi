package com.example.inventoryapi.controller;

import com.example.inventoryapi.model.EanDevice;
import com.example.inventoryapi.service.EanDeviceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class EanDeviceController {

    @Autowired
    EanDeviceService eanDeviceService;

    @RequestMapping(value="/ean_devices", method= RequestMethod.GET)
    public List<EanDevice> getEanDevices () {
        return eanDeviceService.getEanDevices();
    }

    @RequestMapping(value="/ean_devices/id/{ean_device_id}", method= RequestMethod.GET)
    public EanDevice getEanDeviceById(@PathVariable(value = "ean_device_id") Long eanDeviceId) {
        return eanDeviceService.getEanDeviceById(eanDeviceId);
    }

    @RequestMapping(value="/ean_devices/ean/{ean_code}", method= RequestMethod.GET)
    public EanDevice getEanDeviceByEanCode(@PathVariable(value = "ean_code") String eanCode) {
        return eanDeviceService.getEanDeviceByEan(eanCode);
    }
    @RequestMapping(value="/ean_devices/model/{ean_device_model}", method= RequestMethod.GET)
    public EanDevice getEanDeviceByModel(@PathVariable(value = "ean_device_model") String eanDeviceModel) {
        return eanDeviceService.getEanDeviceByModel(eanDeviceModel);
    }

    @RequestMapping(value="/ean_devices", method= RequestMethod.POST)
    public EanDevice createEanDevice (@RequestBody EanDevice eanDevice) {
        return eanDeviceService.createEanDevice(eanDevice);
    }

    @RequestMapping(value="/ean_devices/id/{ean_device_id}", method= RequestMethod.PUT)
    public EanDevice updateEanDevice (@PathVariable(value = "ean_device_id") Long eanDeviceId, @RequestBody EanDevice eanDevice) {
        return eanDeviceService.updateEanDevice(eanDevice, eanDeviceId);
    }

    @RequestMapping(value="/ean_devices/id/{ean_device_id}", method= RequestMethod.DELETE)
    public void deleteEanDevice(@PathVariable(value = "ean_device_id") Long eanDeviceId) {
        eanDeviceService.deleteEanDevice(eanDeviceId);
    }

}
