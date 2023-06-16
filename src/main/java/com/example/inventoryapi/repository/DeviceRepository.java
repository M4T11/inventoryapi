package com.example.inventoryapi.repository;

import com.example.inventoryapi.model.Device;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface DeviceRepository extends JpaRepository<Device, Long> {
    Optional<Device> findBySerialNumber(String serialNumber);
    Optional<Device> findByQrCode(String qrCode);
    Boolean existsBySerialNumber(String serialNumber);



}
