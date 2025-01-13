package com.project.cab.cabApp.services.impl;

import com.project.cab.cabApp.entities.WalletTransaction;
import com.project.cab.cabApp.repositories.WalletTransactionRepository;
import com.project.cab.cabApp.services.WalletTransactionService;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;


@Service
@RequiredArgsConstructor
public class WalletTransactionServiceImpl implements WalletTransactionService {

    private final WalletTransactionRepository walletTransactionRepository;
    private final ModelMapper modelMapper;

    @Override
    public void createNewWalletTransaction(WalletTransaction walletTransaction){
        walletTransactionRepository.save(walletTransaction);
    }


}
