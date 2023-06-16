package com.example.inventoryapi.service;

import com.example.inventoryapi.model.Producer;
import com.example.inventoryapi.repository.ProducerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

import static org.springframework.http.HttpStatus.CONFLICT;
import static org.springframework.http.HttpStatus.NOT_FOUND;

@Service
public class ProducerService {

    @Autowired
    ProducerRepository producerRepository;

    public Producer createProducer(Producer producer) {
        if(!producerRepository.existsByName(producer.getName())) {
            return producerRepository.save(producer);
        } else {
            throw new ResponseStatusException(CONFLICT, "A producer with the given name already exists!");
        }
    }

    public List<Producer> getProducers() {
        return producerRepository.findAll();
    }

    public Producer getProducerById(Long producerId) {
        return producerRepository.findById(producerId).orElseThrow(() -> new ResponseStatusException(NOT_FOUND, "Producer not found!"));
    }

    public Producer getProducerByName(String producerName) {
        return producerRepository.findByName(producerName).orElseThrow(() -> new ResponseStatusException(NOT_FOUND, "Producer not found!"));
    }

    public Producer updateProducer(Producer producer, Long producerId) {
        if(producerRepository.existsById(producerId)) {
            Producer producerToUpdate = producerRepository.findById(producerId).get();
            producerToUpdate.setName(producer.getName());
            return producerRepository.save(producerToUpdate);
        } else {
            throw new ResponseStatusException(NOT_FOUND, "Producer not found!");
        }
    }

    public void deleteProducer(Long producerId) {
        if(producerRepository.existsById(producerId)) {
            producerRepository.deleteById(producerId);
        } else {
            throw new ResponseStatusException(NOT_FOUND, "Producer not found!");
        }

    }






}
