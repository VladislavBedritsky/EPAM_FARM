package org.example.epam.backend.service.impl;

import org.example.epam.backend.service.MailService;
import org.example.epam.mail.service.MailSender;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class MailServiceImpl implements MailService {

    @Autowired
    private MailSender emailSender;

    @Override
    public void sendEmail(String emailTo, String subject, String message) {
        emailSender.sendEmail(emailTo, subject, message);
    }
}
