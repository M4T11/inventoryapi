package com.example.inventoryapi.repository;

import com.example.inventoryapi.model.Producer;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface ProducerRepository extends JpaRepository<Producer, Long> {

    Optional<Producer> findByName(String name);
    Boolean existsByName(String name);

}
