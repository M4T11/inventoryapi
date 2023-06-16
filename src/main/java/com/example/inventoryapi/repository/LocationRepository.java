package com.example.inventoryapi.repository;

import com.example.inventoryapi.model.Location;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface LocationRepository extends JpaRepository<Location, Long> {
    Optional<Location> findByName(String name);
    Boolean existsByName(String name);
}
