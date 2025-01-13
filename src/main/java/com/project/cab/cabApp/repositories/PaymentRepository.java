package com.project.cab.cabApp.repositories;

import com.project.cab.cabApp.entities.Payment;
import com.project.cab.cabApp.entities.Ride;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface PaymentRepository extends JpaRepository<Payment, Long> {
    Optional<Payment> findByRide(Ride ride);
}
