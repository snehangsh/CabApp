package com.project.cab.cabApp.repositories;

import com.project.cab.cabApp.entities.Driver;
import com.project.cab.cabApp.entities.User;
import org.locationtech.jts.geom.Point;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface DriverRepository extends JpaRepository<Driver, Long> {

    //ST_Distance(point1, point2)
    //ST_DWithin(point1, 10000)
    @Query(value = "SELECT d.*, ST_Distance(d.current_location, :pickupLocation) AS distance " +
            "FROM driver AS d " +
            "WHERE available = true AND ST_DWithin(d.current_location, :pickupLocation, 1000) " +
            "ORDER BY distance " +
            "LIMIT 10 ", nativeQuery = true)
    List<Driver> findTenNearestDrivers(Point pickupLocation);



    @Query(value = "SELECT d.* " +
    "FROM driver d " +
    "WHERE d.available = true AND ST_DWithin(d.current_location, :pickupLocation, 15000) "+
    "ORDER BY d.rating DESC "+
    "LIMIT 10 ", nativeQuery = true)
    List<Driver> findTenNearbyTopRatedDrivers(Point pickupLocation);

    Optional<Driver> findByUser(User user);
}
