package com.example.bankapplication.service;

import com.example.bankapplication.dto.*;

public interface UserService {
    BankResponseDTO createAccount(UserRequestDTO userRequestDTO);
    BankResponseDTO balanceEnquiry(EnquiryRequest enquiryRequest);
    String nameEnquiry(EnquiryRequest request);
    BankResponseDTO creditAccount(CreditDebitRequestDTO creditDebitRequestDTO);

    BankResponseDTO debitAccount(CreditDebitRequestDTO debitRequestDTO);
    BankResponseDTO transfer(TransferRequest transferRequest);

}
