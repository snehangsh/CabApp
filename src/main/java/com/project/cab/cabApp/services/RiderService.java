package com.project.cab.cabApp.services;

import com.project.cab.cabApp.dto.DriverDto;
import com.project.cab.cabApp.dto.RideDto;
import com.project.cab.cabApp.dto.RideRequestDto;
import com.project.cab.cabApp.dto.RiderDto;
import com.project.cab.cabApp.entities.Rider;
import com.project.cab.cabApp.entities.User;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;

import java.util.List;

public interface RiderService {
    RideRequestDto requestRide(RideRequestDto rideRequestDto);

    RideDto cancelRide(Long rideId);

    DriverDto rateDriver(Long rideId, Integer rating);

    RiderDto getMyProfile();

    List<RideDto> getAllMyRides();

    Page<RideDto> getAllMyRides(PageRequest pageRequest);

    Rider createNewRider(User user );

    Rider getCurrentRider();
}
