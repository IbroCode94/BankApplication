package com.example.bankapplication.service;

import com.example.bankapplication.dto.CreditDebitRequestDTO;
import com.example.bankapplication.dto.EnquiryRequest;
import com.example.bankapplication.dto.UserRequestDTO;
import com.example.bankapplication.dto.BankResponseDTO;

public interface UserService {
    BankResponseDTO createAccount(UserRequestDTO userRequestDTO);
    BankResponseDTO balanceEnquiry(EnquiryRequest enquiryRequest);
    String nameEnquiry(EnquiryRequest request);
    BankResponseDTO creditAccount(CreditDebitRequestDTO creditDebitRequestDTO);

    BankResponseDTO debitAccount(CreditDebitRequestDTO debitRequestDTO);

}
