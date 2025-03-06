package com.foodmap.foodmap_backend.controllers;

import java.util.List;

import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.foodmap.foodmap_backend.domain.entities.Location;
import com.foodmap.foodmap_backend.services.LocationService;

@RestController
@RequestMapping(path = "/api/user/location")
@CrossOrigin(origins = {"http://localhost:5174", "http://localhost:5173"})
public class LocationController {

    private final LocationService locationService;

    public LocationController(LocationService locationService){
        this.locationService =locationService;
    }

    @PostMapping(
        path = "{userId}/create"
    )
    public Location createLocation(@PathVariable Long userId, @RequestBody Location location){
        return locationService.addLocation(userId, location);
    }

    @GetMapping(path = "{userId}")
    public List<Location> getLocation(@PathVariable Long userId){
        return locationService.listAddress(userId);
    }
}

