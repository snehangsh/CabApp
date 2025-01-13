package com.project.cab.cabApp.services;

import com.project.cab.cabApp.entities.RideRequest;

public interface RideRequestService {
    RideRequest findRideRequestById(Long rideRequestId);
    void update(RideRequest rideRequest);
}
