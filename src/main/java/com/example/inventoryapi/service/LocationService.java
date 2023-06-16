package com.example.inventoryapi.service;


import com.example.inventoryapi.model.Category;
import com.example.inventoryapi.model.Location;
import com.example.inventoryapi.repository.LocationRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;
import java.util.Optional;

import static org.springframework.http.HttpStatus.CONFLICT;
import static org.springframework.http.HttpStatus.NOT_FOUND;

@Service
public class LocationService {

    @Autowired
    LocationRepository locationRepository;

    public Location createLocation(Location location) {
        if(!locationRepository.existsByName(location.getName())) {
            return locationRepository.save(location);
        } else {
            throw new ResponseStatusException(CONFLICT, "A location with the given name already exists!");
        }
    }

    public List<Location> getLocations() {
        return locationRepository.findAll();
    }

    public Location getLocationById(Long locationId) {
        return locationRepository.findById(locationId).orElseThrow(() -> new ResponseStatusException(NOT_FOUND, "Location not found!"));
    }

    public Location getLocationByName(String locationName) {
        return locationRepository.findByName(locationName).orElseThrow(() -> new ResponseStatusException(NOT_FOUND, "Location not found!"));
    }

    public Location updateLocation(Location location, Long locationId) {
        if(locationRepository.existsById(locationId)) {
            Location locationToUpdate = locationRepository.findById(locationId).get();
            locationToUpdate.setName(location.getName());
            return locationRepository.save(locationToUpdate);
        } else {
            throw new ResponseStatusException(NOT_FOUND, "Location not found!");
        }
    }

    public void deleteLocation(Long locationId) {
        if(locationRepository.existsById(locationId)) {
            locationRepository.deleteById(locationId);
        } else {
            throw new ResponseStatusException(NOT_FOUND, "Location not found!");
        }
    }

}
