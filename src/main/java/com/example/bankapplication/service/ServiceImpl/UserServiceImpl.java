package com.example.bankapplication.service.ServiceImpl;

import com.example.bankapplication.dto.*;
import com.example.bankapplication.entity.User;
import com.example.bankapplication.repository.UserRepository;
import com.example.bankapplication.service.EmailService;
import com.example.bankapplication.service.UserService;
import com.example.bankapplication.utils.AccountUtils;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;

@Service
@RequiredArgsConstructor
public class UserServiceImpl  implements UserService {
    private final UserRepository userRepository;
   // private final EmailService  emailService;
    @Override
    public BankResponseDTO createAccount(UserRequestDTO userRequestDTO) {

        if(userRepository.existsByEmail(userRequestDTO.getEmail())){
           return BankResponseDTO.builder()
                    .responseCode(AccountUtils.ACCOUNT_EXISTS_CODE)
                    .responseMessage(AccountUtils.ACCOUNT_EXISTS_MESSAGE)
                    .accountInfo(null)
                    .build();

        }


        User newUser = User.builder()
                .firstName(userRequestDTO.getFirstName())
                .lastName(userRequestDTO.getLastName())
                .otherName(userRequestDTO.getOtherName())
                .gender(userRequestDTO.getGender())
                .address(userRequestDTO.getAddress())
                .stateOfOrigin(userRequestDTO.getStateOfOrigin())
                .accountNumber(AccountUtils.generateAccountNumber())
                .accountBalance(BigDecimal.ZERO)
                .email(userRequestDTO.getEmail())
                .phoneNumber(userRequestDTO.getPhoneNumber())
                .alternativePhoneNumber(userRequestDTO.getAlternativePhoneNumber())
                .dateOfBirth(userRequestDTO.getDateOfBirth())
                .status("ACTIVE")
                .build();
        User saveUser = userRepository.save(newUser);
        //send email
//        EmailDetails emailDetail = EmailDetails.builder()
//                .recipient(saveUser.getEmail())
//                .subject("ACCOUNT CREATION")
//                .messageBody("Congratulations Your Account Has been Successfully Created.\nYour Account Details: \n " +
//                "Account Name: " + saveUser.getFirstName() + " " + saveUser.getLastName() + " " + saveUser.getOtherName() + "\nAccount Number: " + saveUser.getAccountNumber())
//                .build();
//        emailService.sendEmailAlert(emailDetail);

        return BankResponseDTO.builder()
                .responseCode(AccountUtils.ACCOUNT_CREATION_SUCCESS)
                .responseMessage(AccountUtils.ACCOUNT_CREATION_MESSAGE)
                .accountInfo(AccountInfo.builder()
                        .accountBalance(saveUser.getAccountBalance())
                        .accountNumber(saveUser.getAccountNumber())
                        .accountName(saveUser.getFirstName() + " " + saveUser.getLastName() + " " + saveUser.getOtherName() )
                        .build())
                .build();


    }

    @Override
    public BankResponseDTO balanceEnquiry(EnquiryRequest enquiryRequest) {
        //check if the account number exists
        boolean isAccountPresent = userRepository.existsByAccountNumber(enquiryRequest.getAccountNumber());
        if(!isAccountPresent){
            return BankResponseDTO.builder()
                    .responseCode(AccountUtils.ACCOUNT_NOT_EXISTS_CODE)
                    .responseMessage(AccountUtils.ACCOUNT_NOT_EXIST_MESSAGE)
                    .accountInfo(null)
                    .build();
        }
        User foundUser = userRepository.findByAccountNumber(enquiryRequest.getAccountNumber());
        return BankResponseDTO.builder()
                .responseCode(AccountUtils.ACCOUNT_FOUND_CODE)
                .responseMessage(AccountUtils.ACCOUNT_FOUND_SUCCESS)
                .accountInfo(AccountInfo.builder()
                        .accountBalance(foundUser.getAccountBalance())
                        .accountNumber(foundUser.getAccountNumber())
                        .accountName(foundUser.getFirstName() + " " + foundUser.getLastName() + " " + foundUser.getOtherName())
                        .build())
                .build();

    }

    @Override
    public String nameEnquiry(EnquiryRequest request) {
        boolean isAccountPresent = userRepository.existsByAccountNumber(request.getAccountNumber());
        if (!isAccountPresent) {
            return AccountUtils.ACCOUNT_NOT_EXIST_MESSAGE;
        }
        User foundUser = userRepository.findByAccountNumber(request.getAccountNumber());
        return foundUser.getFirstName() + " " + foundUser.getLastName() + " " + foundUser.getOtherName();
    }

    @Override
    public BankResponseDTO creditAccount(CreditDebitRequestDTO requestDTO) {
        boolean isAccountPresent = userRepository.existsByAccountNumber(requestDTO.getAccountNumber());
        if(!isAccountPresent) {
            return BankResponseDTO.builder()
                    .responseCode(AccountUtils.ACCOUNT_NOT_EXISTS_CODE)
                    .responseMessage(AccountUtils.ACCOUNT_NOT_EXIST_MESSAGE)
                    .accountInfo(null)
                    .build();
        }
        User creditUser = userRepository.findByAccountNumber(requestDTO.getAccountNumber());
        creditUser.setAccountBalance(creditUser.getAccountBalance().add(requestDTO.getAmount()));
        userRepository.save(creditUser);
        return  BankResponseDTO.builder()
                .responseCode(AccountUtils.ACCOUNT_CREDITED_CODE)
                .responseMessage(AccountUtils.ACCOUNT_CREDITED_SUCCESS_MESSAGE)
                .accountInfo(AccountInfo.builder()
                        .accountName(creditUser.getFirstName() + " " + creditUser.getLastName() + " " + creditUser.getOtherName())
                        .accountBalance(creditUser.getAccountBalance())
                        .accountNumber(requestDTO.getAccountNumber())
                        .build())
                .build();
    }

    @Override
    public BankResponseDTO debitAccount(CreditDebitRequestDTO debitRequestDTO) {
        boolean isPresent = userRepository.existsByAccountNumber(debitRequestDTO.getAccountNumber());
        if(!isPresent) {
            return BankResponseDTO.builder()
                    .responseCode(AccountUtils.ACCOUNT_NOT_EXISTS_CODE)
                    .responseMessage(AccountUtils.ACCOUNT_NOT_EXIST_MESSAGE)
                    .accountInfo(null)
                    .build();
        }
        User userToDebit = userRepository.findByAccountNumber(debitRequestDTO.getAccountNumber());
        BigDecimal availableBalance = userToDebit.getAccountBalance();
        BigDecimal debitAmount = debitRequestDTO.getAmount();
        if(availableBalance.compareTo(debitAmount) < 0){
            return BankResponseDTO.builder()
                    .responseCode(AccountUtils.INSUFFICIENT_BALANCE_CODE)
                    .responseMessage(AccountUtils.INSUFFICIENT_BALANCE_MESSAGE)
                    .accountInfo(null)
                    .build();

        }
        else{
            userToDebit.setAccountBalance(userToDebit.getAccountBalance().subtract(debitRequestDTO.getAmount()));
            userRepository.save(userToDebit);
            return BankResponseDTO.builder()
                    .responseCode(AccountUtils.ACCOUNT_DEBITED_SUCCESS_CODE)
                    .responseMessage(AccountUtils.ACCOUNT_DEBITED_MESSAGE)
                    .accountInfo(AccountInfo.builder()
                            .accountNumber(debitRequestDTO.getAccountNumber())
                            .accountName(userToDebit.getFirstName() + " " + userToDebit.getLastName() + " " + userToDebit.getOtherName())
                            .accountBalance(userToDebit.getAccountBalance())
                            .build())
                    .build();
        }
    }
}
