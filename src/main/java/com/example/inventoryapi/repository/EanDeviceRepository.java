package com.example.inventoryapi.repository;

import com.example.inventoryapi.model.EanDevice;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface EanDeviceRepository extends JpaRepository<EanDevice, Long> {

    Boolean existsByEanOrModel(String ean, String model);
    Optional<EanDevice> findByEan(String ean);
    Optional<EanDevice> findByModel(String model);



}
