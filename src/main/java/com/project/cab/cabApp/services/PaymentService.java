package com.project.cab.cabApp.services;

import com.project.cab.cabApp.entities.Payment;
import com.project.cab.cabApp.entities.Ride;
import com.project.cab.cabApp.entities.enums.PaymentStatus;

public interface PaymentService {
    void processPayment(Ride ride);
    Payment createNewPayment(Ride ride);
    void updatePaymentStatus(Payment payment, PaymentStatus paymentStatus);
}
