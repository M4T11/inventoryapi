package com.example.inventoryapi.service;

import com.example.inventoryapi.model.*;
import com.example.inventoryapi.repository.DeviceRepository;
import com.example.inventoryapi.repository.EanDeviceRepository;
import com.example.inventoryapi.repository.LocationRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.sql.Date;
import java.time.LocalDate;
import java.util.List;

import static org.springframework.http.HttpStatus.CONFLICT;
import static org.springframework.http.HttpStatus.NOT_FOUND;

@Service
public class DeviceService {

    @Autowired
    DeviceRepository deviceRepository;

    @Autowired
    EanDeviceRepository eanDeviceRepository;

    @Autowired
    LocationRepository locationRepository;

    @Autowired
    DeviceHistoryService deviceHistoryService;

    public List<Device> getDevices() {
        return deviceRepository.findAll();
    }

    public Device getDeviceById(Long deviceId) {
        return deviceRepository.findById(deviceId).orElseThrow(() -> new ResponseStatusException(NOT_FOUND, "Device not found!"));
    }

    public Device getDeviceBySerialNumber(String serialNumber) {
        return deviceRepository.findBySerialNumber(serialNumber).orElseThrow(() -> new ResponseStatusException(NOT_FOUND, "Device not found!"));
    }

    public Device getDeviceByQrCode(String qrCode) {
        return deviceRepository.findByQrCode(qrCode).orElseThrow(() -> new ResponseStatusException(NOT_FOUND, "Device not found!"));
    }

    public Device createDevice(Device device) {
        if(!deviceRepository.existsBySerialNumber(device.getSerialNumber())) {
            if (eanDeviceRepository.existsById(device.getEanDevice().getEanDeviceId())) {
                EanDevice eanDevice = eanDeviceRepository.findById(device.getEanDevice().getEanDeviceId()).get();
                device.setEanDevice(eanDevice);
            } else {
                throw new ResponseStatusException(NOT_FOUND, "EanDevice not found!");
            }
            if (locationRepository.existsById(device.getLocation().getLocationId())) {
                Location location = locationRepository.findById(device.getLocation().getLocationId()).get();
                device.setLocation(location);
            } else {
                throw new ResponseStatusException(NOT_FOUND, "Location not found!");
            }
            device.setDateAdded(Date.valueOf(LocalDate.now()));

            return deviceRepository.save(device);

        } else {
            throw new ResponseStatusException(CONFLICT, "A device with the given serial number already exists!");
        }
    }

    public Device updateDevice(Device device, Long deviceId) {
        if(deviceRepository.existsById(device.getDeviceId())) {
            Device deviceToUpdate = deviceRepository.findById(deviceId).get();
            Long oldLocationId = deviceToUpdate.getLocation().getLocationId();
            String oldLocationName = deviceToUpdate.getLocation().getName();
            int oldQuantity = deviceToUpdate.getQuantity();
            boolean oldReturned = deviceToUpdate.isReturned();
            String oldStatus = deviceToUpdate.getStatus();

            if (eanDeviceRepository.existsById(device.getEanDevice().getEanDeviceId())) {
                EanDevice eanDevice = eanDeviceRepository.findById(device.getEanDevice().getEanDeviceId()).get();
                deviceToUpdate.setEanDevice(eanDevice);
            } else {
                throw new ResponseStatusException(NOT_FOUND, "EanDevice not found!");
            }
            if (locationRepository.existsById(device.getLocation().getLocationId())) {
                Location location = locationRepository.findById(device.getLocation().getLocationId()).get();
                deviceToUpdate.setLocation(location);
            } else {
                throw new ResponseStatusException(NOT_FOUND, "Location not found!");
            }

            deviceToUpdate.setName(device.getName());
            deviceToUpdate.setSerialNumber(device.getSerialNumber());
            deviceToUpdate.setDescription(device.getDescription());
            deviceToUpdate.setQuantity(device.getQuantity());
            deviceToUpdate.setCondition(device.getCondition());
            deviceToUpdate.setStatus(device.getStatus());
            deviceToUpdate.setDateAdded(deviceToUpdate.getDateAdded());
            deviceToUpdate.setQrCode(device.getQrCode());
            deviceToUpdate.setReturned(device.isReturned());

            if(!oldLocationId.equals(device.getLocation().getLocationId())) {
                deviceHistoryService.createDeviceHistory(deviceToUpdate, "Zmiana lokalizacji urządzenia: " + oldLocationName + " -> " + device.getLocation().getName());
            }
            if(oldQuantity != device.getQuantity()) {
                deviceHistoryService.createDeviceHistory(deviceToUpdate, "Zmiana ilości sztuk: " + oldQuantity + " -> " + device.getQuantity());
            }
            if(oldReturned != device.isReturned()) {
                deviceHistoryService.createDeviceHistory(deviceToUpdate, "Zarejestrowano zwrot urządzenia (ID: " + device.getQrCode() + ")");
            }
            if(!oldStatus.equals(device.getStatus())) {
                deviceHistoryService.createDeviceHistory(deviceToUpdate, "Zmiana statusu urządzenia: " + oldStatus + " -> " + device.getStatus());
            }

            return deviceRepository.save(deviceToUpdate);

        } else {
            throw new ResponseStatusException(NOT_FOUND, "Device not found!");
        }
    }

    public void deleteDevice(Long deviceId) {
        if(deviceRepository.existsById(deviceId)) {
            deviceRepository.deleteById(deviceId);
        } else {
            throw new ResponseStatusException(NOT_FOUND, "Device not found!");
        }
    }


}
