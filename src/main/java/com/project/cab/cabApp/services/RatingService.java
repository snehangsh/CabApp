package com.project.cab.cabApp.services;

import com.project.cab.cabApp.dto.DriverDto;
import com.project.cab.cabApp.dto.RiderDto;
import com.project.cab.cabApp.entities.Ride;

public interface RatingService {
    DriverDto rateDriver(Ride ride, Integer rating);
    RiderDto rateRider(Ride ride, Integer rating);

    void createNewRating(Ride ride);
}
