package com.project.cab.cabApp.strategies.impl;

import com.project.cab.cabApp.entities.Driver;
import com.project.cab.cabApp.entities.RideRequest;
import com.project.cab.cabApp.repositories.DriverRepository;
import com.project.cab.cabApp.strategies.DriverMatchingStrategy;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class DriverMatchingNearestDriverStrategy implements DriverMatchingStrategy {

    private final DriverRepository driverRepository;
    @Override
    public List<Driver> findMatchingDriver(RideRequest rideRequest){
        return driverRepository.findTenNearestDrivers(rideRequest.getPickupLocation());
    }
}
