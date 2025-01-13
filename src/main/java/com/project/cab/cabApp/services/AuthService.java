package com.project.cab.cabApp.services;

import com.project.cab.cabApp.dto.DriverDto;
import com.project.cab.cabApp.dto.SignupDto;
import com.project.cab.cabApp.dto.UserDto;

public interface AuthService {
    String[] login(String email, String password);

    UserDto signup(SignupDto sighupDto);

    DriverDto onboardNewDriver(Long userId, String vehicleId);

    String refreshToken(String refreshToken);
}
