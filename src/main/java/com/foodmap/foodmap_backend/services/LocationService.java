package com.foodmap.foodmap_backend.services;

import java.util.List;

import com.foodmap.foodmap_backend.domain.entities.Location;

public interface LocationService {

    Location addLocation(Long userId, Location location);
    Location updateLocation(Long locationId, Location location);
    List<Location> listAddress(Long userId);
}
