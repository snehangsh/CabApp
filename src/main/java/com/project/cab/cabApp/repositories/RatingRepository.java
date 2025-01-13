package com.project.cab.cabApp.repositories;

import com.project.cab.cabApp.entities.Driver;
import com.project.cab.cabApp.entities.Rating;
import com.project.cab.cabApp.entities.Ride;
import com.project.cab.cabApp.entities.Rider;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface RatingRepository extends JpaRepository<Rating, Long> {
    List<Rating> findByRider(Rider rider);
    List<Rating> findByDriver(Driver driver);
    Optional<Rating> findByRide(Ride ride);
}
