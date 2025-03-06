package com.foodmap.foodmap_backend.repositories;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.foodmap.foodmap_backend.domain.entities.User;

@Repository
public interface UserRepository extends JpaRepository< User, String> {
    Optional<User> findByUsername(String username);

    Optional<User> findById(Long userId);
    Boolean existsByUsername(String username);
    Optional<User> findByEmail(String email);;

}
