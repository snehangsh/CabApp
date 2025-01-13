package com.project.cab.cabApp.strategies;

import com.project.cab.cabApp.entities.Driver;
import com.project.cab.cabApp.entities.Payment;
import com.project.cab.cabApp.entities.Rider;
import com.project.cab.cabApp.entities.enums.PaymentStatus;
import com.project.cab.cabApp.entities.enums.TransactionMethod;
import com.project.cab.cabApp.repositories.PaymentRepository;
import com.project.cab.cabApp.services.WalletService;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class WalletPaymentStrategy implements PaymentStrategy{

    private final WalletService walletService;
    private final PaymentRepository paymentRepository;

    @Override
    @Transactional
    public void processPayment(Payment payment){
        Driver driver = payment.getRide().getDriver();
        Rider rider = payment.getRide().getRider();
        walletService.deductMoneyFromWallet(rider.getUser(), payment.getAmount(), null, payment.getRide(), TransactionMethod.RIDE);
        double driversCut = payment.getAmount() * (1-PLATFORM_COMMISSION);
        walletService.addMoneyToWallet(driver.getUser(), driversCut, null, payment.getRide(), TransactionMethod.RIDE);
        payment.setPaymentStatus(PaymentStatus.CONFIRMED);
        paymentRepository.save(payment);
    }
}
