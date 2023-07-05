package com.example.bankapplication.service;

import com.example.bankapplication.dto.EmailDetails;

public interface EmailService {
    public void sendEmailAlert(EmailDetails emailDetails);

}
