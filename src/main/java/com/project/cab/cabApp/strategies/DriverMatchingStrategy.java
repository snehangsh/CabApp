package com.project.cab.cabApp.strategies;

import com.project.cab.cabApp.entities.Driver;
import com.project.cab.cabApp.entities.RideRequest;

import java.util.List;

public interface DriverMatchingStrategy {
    List<Driver> findMatchingDriver(RideRequest rideRequest);

}
