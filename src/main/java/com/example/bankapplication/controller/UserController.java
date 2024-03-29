package com.example.bankapplication.controller;

import com.example.bankapplication.dto.*;
import com.example.bankapplication.service.EmailService;
import com.example.bankapplication.service.UserService;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.persistence.Table;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/user")
@RequiredArgsConstructor
@Tag(name = "User Account Management APIs")
public class UserController {
    private final UserService userService;
    private  final EmailService emailService;
    @PostMapping("/create")
    public ResponseEntity<BankResponseDTO> creatAccount(@RequestBody UserRequestDTO userRequestDTO){
        BankResponseDTO createUser = userService.createAccount(userRequestDTO);
        return new ResponseEntity(createUser, HttpStatus.OK);
    }
    @GetMapping("/balanceEnquiry")
    public ResponseEntity<BankResponseDTO> balanceEnquiry(@RequestBody EnquiryRequest enquiryRequest){
       BankResponseDTO bankBalance = userService.balanceEnquiry(enquiryRequest);
       return new ResponseEntity<>(bankBalance, HttpStatus.OK);
    }
    @GetMapping("/nameEnquiry")
    public  ResponseEntity<String> nameEnquiry(@RequestBody EnquiryRequest enquiryRequest){
         String result = userService.nameEnquiry(enquiryRequest);
         return new ResponseEntity<>(result, HttpStatus.OK);

    }
    @PostMapping("/credit")
    public  ResponseEntity<BankResponseDTO> creditAccount(@RequestBody CreditDebitRequestDTO requestDTO){
        BankResponseDTO creditedUserAccount = userService.creditAccount(requestDTO);
        return new ResponseEntity<>(creditedUserAccount, HttpStatus.OK);
    }
    @PostMapping("/debit")
    public  ResponseEntity<BankResponseDTO> debitAmount(@RequestBody CreditDebitRequestDTO requestDTO) {
      BankResponseDTO debitAmount =   userService.debitAccount(requestDTO);
      return new ResponseEntity<>(debitAmount, HttpStatus.OK);
    }
    @PostMapping("/transfer")
    public ResponseEntity<BankResponseDTO> transfer (@RequestBody TransferRequest request){
     BankResponseDTO transferSum =  userService.transfer(request);
     return new ResponseEntity<>(transferSum, HttpStatus.OK);
    }

}
