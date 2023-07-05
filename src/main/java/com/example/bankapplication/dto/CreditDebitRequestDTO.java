package com.example.bankapplication.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
@Data
@AllArgsConstructor
@NoArgsConstructor
public class CreditDebitRequestDTO {
    private String AccountNumber;
    private BigDecimal amount;
}
