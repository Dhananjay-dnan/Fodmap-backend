package com.foodmap.foodmap_backend.repositories;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.foodmap.foodmap_backend.domain.entities.Location;

@Repository
public interface LocationRepository extends JpaRepository<Location, Long>{
   List<Location> findByUserId(Long userId);
   @SuppressWarnings("null")
   Optional<Location> findById(Long Id); 
}
