package com.project.cab.cabApp.services;

import com.project.cab.cabApp.entities.Ride;
import com.project.cab.cabApp.entities.User;
import com.project.cab.cabApp.entities.Wallet;
import com.project.cab.cabApp.entities.enums.TransactionMethod;

public interface WalletService {
    Wallet addMoneyToWallet(User user, Double amount, String transactionId, Ride ride, TransactionMethod transactionMethod);
    Wallet deductMoneyFromWallet(User user, Double amount, String transactionId, Ride ride, TransactionMethod transactionMethod);
    void withdrawAllMyMoneyFromWallet();
    Wallet findWalledById(Long walletId);
    Wallet createNewWallet(User user);
    Wallet findByUser(User user);
}
