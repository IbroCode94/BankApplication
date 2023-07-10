package com.example.bankapplication.utils;

import java.time.Year;

public class AccountUtils {

    public static  final String ACCOUNT_EXISTS_CODE = "001";
     public static  final String ACCOUNT_EXISTS_MESSAGE = "This User Already has An Account Created";
     public static  final String ACCOUNT_CREATION_SUCCESS = "002";
     public static  final String ACCOUNT_CREATION_MESSAGE = "Account  Has Been Successfully Created";
    public static  final String ACCOUNT_NOT_EXISTS_CODE = "003";
    public static  final String ACCOUNT_NOT_EXIST_MESSAGE = "User with the provided Account Number is Not Found";
    public static  final String ACCOUNT_FOUND_CODE = "004";
    public static  final String ACCOUNT_FOUND_SUCCESS= "User with the provided Account is Found";
    public static  final String ACCOUNT_CREDITED_CODE= "005";
    public static  final String ACCOUNT_CREDITED_SUCCESS_MESSAGE= "User Account has Been Successfully Credited";
    public  static  final String  INSUFFICIENT_BALANCE_CODE = "006";
    public  static  final String INSUFFICIENT_BALANCE_MESSAGE = "Insufficient Balance";
    public  static  final String ACCOUNT_DEBITED_SUCCESS_CODE = "007";
    public  static final String  ACCOUNT_DEBITED_MESSAGE = "Account has been successfully Debited";
    public  static final String TRANSFER_SUCCESSFUL_CODE = "008";
    public  static  final String TRANSFER_SUCCESSFUL_MESSAGE= "Transfer is Successful";

    /**
     * 2023*  + randomSixDigits
     */
     public  static String generateAccountNumber(){
         Year currentYear = Year.now();
         int min = 100000;
         int max = 999999;
         // generate a random number between min and max

         int randNumber = (int) Math.floor(Math.random() * (max - min + 1) + min);
         //convert the current year and randomNumber to String

         String year = String.valueOf(currentYear);
         String randomNumber = String.valueOf(randNumber);
         StringBuilder accountNumber = new StringBuilder();
       return  accountNumber.append(year).append(randomNumber).toString();
     }

}
