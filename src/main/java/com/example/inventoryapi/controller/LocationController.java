package com.example.inventoryapi.controller;

import com.example.inventoryapi.model.Location;
import com.example.inventoryapi.service.LocationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RestController
public class LocationController {

    @Autowired
    LocationService locationService;

    @RequestMapping(value="/locations", method= RequestMethod.GET)
    public List<Location> getLocations () {
        return locationService.getLocations();
    }

    @RequestMapping(value="/locations/id/{locations_id}", method= RequestMethod.GET)
    public Location getLocationById(@PathVariable(value = "location_id") Long locationId) {

        return locationService.getLocationById(locationId);
    }

    @RequestMapping(value="/locations/name/{locations_name}", method= RequestMethod.GET)
    public Location getLocationByName(@PathVariable(value = "location_name") String locationName) {

        return locationService.getLocationByName(locationName);
    }

    @RequestMapping(value="/locations", method= RequestMethod.POST)
    public Location createLocation(@RequestBody Location location) {
        return locationService.createLocation(location);
    }

    @RequestMapping(value="/locations/id/{location_id}", method= RequestMethod.DELETE)
    public void deleteLocation(@PathVariable(value = "location_id") Long locationId) {
        locationService.deleteLocation(locationId);
    }

}
