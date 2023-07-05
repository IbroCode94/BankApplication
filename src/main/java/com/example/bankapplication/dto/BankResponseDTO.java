package com.example.bankapplication.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class BankResponseDTO {
    private String responseCode;
    private String responseMessage;
    private AccountInfo accountInfo;
}
