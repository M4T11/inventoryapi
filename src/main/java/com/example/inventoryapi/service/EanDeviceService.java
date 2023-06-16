package com.example.inventoryapi.service;

import com.example.inventoryapi.model.Category;
import com.example.inventoryapi.model.EanDevice;
import com.example.inventoryapi.model.Producer;
import com.example.inventoryapi.repository.CategoryRepository;
import com.example.inventoryapi.repository.EanDeviceRepository;
import com.example.inventoryapi.repository.ProducerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;
import java.util.Optional;

import static org.springframework.http.HttpStatus.CONFLICT;
import static org.springframework.http.HttpStatus.NOT_FOUND;

@Service
public class EanDeviceService {

    @Autowired
    EanDeviceRepository eanDeviceRepository;
    @Autowired
    CategoryRepository categoryRepository;
    @Autowired
    ProducerRepository producerRepository;

    public EanDevice createEanDevice(EanDevice eanDevice) {
        if(!eanDeviceRepository.existsByEanOrModel(eanDevice.getEan(), eanDevice.getModel())) {
            if(categoryRepository.existsById(eanDevice.getCategory().getCategoryId())) {
                Category category = categoryRepository.findById(eanDevice.getCategory().getCategoryId()).get();
                eanDevice.setCategory(category);
            } else {
                throw new ResponseStatusException(NOT_FOUND, "Category not found!");
            }
            if(producerRepository.existsById(eanDevice.getProducer().getProducerId())) {
                Producer producer = producerRepository.findById(eanDevice.getProducer().getProducerId()).get();
                eanDevice.setProducer(producer);
            } else {
                throw new ResponseStatusException(NOT_FOUND, "Producer not found!");
            }

            return eanDeviceRepository.save(eanDevice);
        } else {
            throw new ResponseStatusException(CONFLICT, "A EanDevice with the given details already exists!");
        }
    }

    public EanDevice updateEanDevice(EanDevice eanDevice, Long eanDeviceId) {
        if(eanDeviceRepository.existsById(eanDeviceId)) {
            EanDevice eanDeviceToUpdate = eanDeviceRepository.findById(eanDeviceId).get();
            if(categoryRepository.existsById(eanDevice.getCategory().getCategoryId())) {
                Category category = categoryRepository.findById(eanDevice.getCategory().getCategoryId()).get();
                eanDeviceToUpdate.setCategory(category);
            } else {
                throw new ResponseStatusException(NOT_FOUND, "Category not found!");
            }
            if(producerRepository.existsById(eanDevice.getProducer().getProducerId())) {
                Producer producer = producerRepository.findById(eanDevice.getProducer().getProducerId()).get();
                eanDeviceToUpdate.setProducer(producer);
            } else {
                throw new ResponseStatusException(NOT_FOUND, "Producer not found!");
            }
            eanDeviceToUpdate.setEan(eanDevice.getEan());
            eanDeviceToUpdate.setModel(eanDevice.getModel());

            return eanDeviceRepository.save(eanDeviceToUpdate);
            
        } else {
            throw new ResponseStatusException(NOT_FOUND, "EanDevice not found!");
        }
    }

    public List<EanDevice> getEanDevices() {
        return eanDeviceRepository.findAll();
    }

    public EanDevice getEanDeviceById(Long eanDeviceId) {
        return eanDeviceRepository.findById(eanDeviceId).orElseThrow(() -> new ResponseStatusException(NOT_FOUND, "EanDevice not found!"));
    }

    public EanDevice getEanDeviceByEan(String eanCode) {
        return eanDeviceRepository.findByEan(eanCode).orElseThrow(() -> new ResponseStatusException(NOT_FOUND, "EanDevice not found!"));
    }

    public EanDevice getEanDeviceByModel(String model) {
        return eanDeviceRepository.findByModel(model).orElseThrow(() -> new ResponseStatusException(NOT_FOUND, "EanDevice not found!"));
    }

    public void deleteEanDevice(Long eanDeviceId) {
        if(eanDeviceRepository.existsById(eanDeviceId)) {
            eanDeviceRepository.deleteById(eanDeviceId);
        } else {
            throw new ResponseStatusException(NOT_FOUND, "EanDevice not found!");
        }
    }


}
