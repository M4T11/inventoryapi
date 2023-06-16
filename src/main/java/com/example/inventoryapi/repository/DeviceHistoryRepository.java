package com.example.inventoryapi.repository;

import com.example.inventoryapi.model.DeviceHistory;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface DeviceHistoryRepository extends JpaRepository<DeviceHistory, Long> {

    List<DeviceHistory> findAllByDeviceDeviceId(Long deviceId);

}
