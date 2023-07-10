package com.example.bankapplication.service;

import com.example.bankapplication.dto.TransactionDTO;

public interface TransactionService {
    void saveTransaction(TransactionDTO transactionDTO);
}
