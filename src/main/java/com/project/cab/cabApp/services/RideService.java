package com.project.cab.cabApp.services;

import com.project.cab.cabApp.entities.Driver;
import com.project.cab.cabApp.entities.Ride;
import com.project.cab.cabApp.entities.RideRequest;
import com.project.cab.cabApp.entities.Rider;
import com.project.cab.cabApp.entities.enums.RideStatus;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;

public interface RideService {

    Ride getRideById(Long rideId);

    //void matchWithDrivers(RideRequestDto rideRequestDto);

    Ride createNewRide(RideRequest rideRequest, Driver driver);

    Ride updateRideStatus(Ride ride, RideStatus rideStatus);

    Page<Ride> getAllRidesOfRider(Rider rider, PageRequest pageRequest);

    Page<Ride> getAllRidesOfDriver(Driver driver, PageRequest pageRequest);

}
