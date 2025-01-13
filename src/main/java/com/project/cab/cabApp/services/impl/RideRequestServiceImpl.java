package com.project.cab.cabApp.services.impl;

import com.project.cab.cabApp.entities.RideRequest;
import com.project.cab.cabApp.exceptions.ResourceNotFoundException;
import com.project.cab.cabApp.repositories.RideRequestRepository;
import com.project.cab.cabApp.services.RideRequestService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class  RideRequestServiceImpl implements RideRequestService {

    private final RideRequestRepository rideRequestRepository;

    @Override
    public RideRequest findRideRequestById(Long rideRequestId){
        return rideRequestRepository.findById(rideRequestId)
                .orElseThrow(() -> new ResourceNotFoundException("RideRequest not found with id: " +rideRequestId));
    }

    @Override
    public void update(RideRequest rideRequest) {
        RideRequest toSave = rideRequestRepository.findById(rideRequest.getId())
                .orElseThrow(()-> new ResourceNotFoundException("RideRequest not found with Id "+ rideRequest.getId()));
        rideRequestRepository.save(rideRequest);
    }
}
