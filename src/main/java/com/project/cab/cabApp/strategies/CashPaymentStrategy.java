package com.project.cab.cabApp.strategies;

import com.project.cab.cabApp.entities.Driver;
import com.project.cab.cabApp.entities.Payment;
import com.project.cab.cabApp.entities.enums.PaymentStatus;
import com.project.cab.cabApp.entities.enums.TransactionMethod;
import com.project.cab.cabApp.repositories.PaymentRepository;
import com.project.cab.cabApp.services.WalletService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class CashPaymentStrategy implements PaymentStrategy{

    private final WalletService walletService;
    private final PaymentRepository paymentRepository;

    @Override
    public void processPayment(Payment payment) {
        Driver driver = payment.getRide().getDriver();
        Double platformCommission = payment.getAmount() * PLATFORM_COMMISSION;
        walletService.deductMoneyFromWallet(driver.getUser(), platformCommission, null, payment.getRide(), TransactionMethod.RIDE);
        payment.setPaymentStatus(PaymentStatus.CONFIRMED);
        paymentRepository.save(payment);
    }
}
