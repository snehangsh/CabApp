package com.project.cab.cabApp.services.impl;

import com.project.cab.cabApp.dto.DriverDto;
import com.project.cab.cabApp.dto.SignupDto;
import com.project.cab.cabApp.dto.UserDto;
import com.project.cab.cabApp.entities.Driver;
import com.project.cab.cabApp.entities.User;
import com.project.cab.cabApp.entities.enums.Role;
import com.project.cab.cabApp.exceptions.ResourceNotFoundException;
import com.project.cab.cabApp.exceptions.RuntimeConflictException;
import com.project.cab.cabApp.repositories.UserRepository;
import com.project.cab.cabApp.security.JWTService;
import com.project.cab.cabApp.services.AuthService;
import com.project.cab.cabApp.services.DriverService;
import com.project.cab.cabApp.services.RiderService;
import com.project.cab.cabApp.services.WalletService;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Set;

import static com.project.cab.cabApp.entities.enums.Role.DRIVER;

@Service
@RequiredArgsConstructor
public class AuthServiceImpl implements AuthService {

    private final ModelMapper modelMapper;
    private final UserRepository userRepository;
    private final RiderService riderService;
    private final WalletService walletService;
    private final DriverService driverService;
    private final PasswordEncoder passwordEncoder;
    private final AuthenticationManager authenticationManager;
    private final JWTService jwtService;


    @Override
    public String[] login(String email, String password){
        Authentication authentication =  authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(email,password)
        );
        User user = (User) authentication.getPrincipal();
        String accessToken = jwtService.generateAccessToken(user);
        String refreshToken = jwtService.generateRefreshToken(user);
        return new String[] {accessToken, refreshToken};

    }

    @Override
    @Transactional
    public UserDto signup(SignupDto signupDto){
        User user = userRepository.findByEmail(signupDto.getEmail()).orElse(null);
        if(user !=null)
                throw new RuntimeConflictException("Cannot signup, User already exists with email "+ signupDto.getEmail());
        User mappedUser = modelMapper.map(signupDto, User.class);
        mappedUser.setRoles(Set.of(Role.RIDER));
        mappedUser.setPassword(passwordEncoder.encode(mappedUser.getPassword()));
        User savedUser = userRepository.save(mappedUser);
        //creating user related entities
        riderService.createNewRider(savedUser);
        walletService.createNewWallet(savedUser);
        return modelMapper.map(savedUser, UserDto.class);
    }

    @Override
    public DriverDto onboardNewDriver(Long userId, String vehicleId){
        User user = userRepository.findById(userId)
                .orElseThrow(() -> new ResourceNotFoundException("User not found with userId: " + userId));
        if(user.getRoles().contains(DRIVER)) throw new RuntimeConflictException("user with id: "+userId+" is already a driver");
        Driver createDriver = Driver.builder()
                .user(user)
                .rating(0.0)
                .vehicleId(vehicleId)
                .available(true)
                .build();
        user.getRoles().add(DRIVER);
        userRepository.save(user);
        Driver savedDriver = driverService.createNewDriver(createDriver);
        return modelMapper.map(savedDriver, DriverDto.class);
    }

    @Override
    public String refreshToken(String refreshToken) {
        Long userId = jwtService.getUserIdFromToken(refreshToken);
        User user = userRepository.findById(userId).orElseThrow(()-> new ResourceNotFoundException("User not found with id: "+ userId));
        return jwtService.generateAccessToken(user);
    }
}
