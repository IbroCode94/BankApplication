package com.example.bankapplication.service.ServiceImpl;

import com.example.bankapplication.dto.TransactionDTO;
import com.example.bankapplication.entity.Transaction;
import com.example.bankapplication.repository.TransactionRepository;
import com.example.bankapplication.service.TransactionService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class TransactionImpl implements TransactionService {
    private final TransactionRepository transactionRepository;
    @Override
    public void saveTransaction(TransactionDTO transactionDTO) {
        Transaction transactionDTO1 = Transaction.builder()
                .transactionType(transactionDTO.getTransactionType())
                .accountNumber(transactionDTO.getAccountNumber())
                .amount(transactionDTO.getAmount())
                .status("SUCCESS")
                .build();
        transactionRepository.save(transactionDTO1);
    }
}
