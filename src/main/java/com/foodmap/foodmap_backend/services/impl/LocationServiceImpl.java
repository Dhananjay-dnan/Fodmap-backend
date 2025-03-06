package com.foodmap.foodmap_backend.services.impl;

import java.util.List;
import java.util.NoSuchElementException;

import org.springframework.stereotype.Service;

import com.foodmap.foodmap_backend.domain.entities.Location;
import com.foodmap.foodmap_backend.domain.entities.User;
import com.foodmap.foodmap_backend.repositories.LocationRepository;
import com.foodmap.foodmap_backend.repositories.UserRepository;
import com.foodmap.foodmap_backend.services.LocationService;

@Service
public class LocationServiceImpl implements LocationService{

    private final UserRepository userRepository;
    private final LocationRepository locationRepository;
    public LocationServiceImpl(UserRepository userRepository, LocationRepository locationRepository){
        this.userRepository = userRepository;
        this.locationRepository = locationRepository;
    }

    @Override
    public Location addLocation(Long userId, Location location) {
        User user = userRepository.findById(userId)
                .orElseThrow(() -> new NoSuchElementException("Restaurant not found with ID: " + userId));
    
        location.setUser(user);
        return locationRepository.save(location);
    }

    @Override
    public Location updateLocation(Long locationId, Location location) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'updateLocation'");
    }

    @Override
    public List<Location> listAddress(Long userId) {
        return locationRepository.findByUserId(userId);
    }
    
}
