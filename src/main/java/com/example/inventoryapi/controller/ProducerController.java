package com.example.inventoryapi.controller;

import com.example.inventoryapi.model.Producer;
import com.example.inventoryapi.service.ProducerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class ProducerController {
    @Autowired
    ProducerService producerService;

    @RequestMapping(value="/producers", method= RequestMethod.GET)
    public List<Producer> getProducers() {
        return producerService.getProducers();
    }

    @RequestMapping(value="/producers/id/{producer_id}", method= RequestMethod.GET)
    public Producer getProducerById(@PathVariable(value = "producer_id") Long producerId) {

        return producerService.getProducerById(producerId);

    }

    @RequestMapping(value="/producers/name/{producer_name}", method= RequestMethod.GET)
    public Producer getProducerByName(@PathVariable(value = "producer_name") String producerName) {

        return producerService.getProducerByName(producerName);

    }

    @RequestMapping(value="/producers", method= RequestMethod.POST)
    public Producer createProducer(@RequestBody Producer producer) {
        return producerService.createProducer(producer);
    }

    @RequestMapping(value="/producers/id/{producer_id}", method= RequestMethod.PUT)
    public Producer updateProducer(@PathVariable(value="producer_id") Long producerId, @RequestBody Producer producer) {
        return producerService.updateProducer(producer, producerId);
    }

    @RequestMapping(value="/producers/id/{producer_id}", method= RequestMethod.DELETE)
    public void deleteProducer (@PathVariable(value="producer_id") Long producerId) {
        producerService.deleteProducer(producerId);
    }



}
