package org.example.epam.service;

/**
 * MailSender Service
 *
 * @version 1.01 01 Jun 2020
 * @author Uladzislau Biadrytski
 */
public interface MailSender {

    /**
     * Send an email.
     *
     * @param emailTo Recipient email
     * @param subject Email topic
     * @param message Email message
     */
    void sendEmail (String emailTo, String subject, String message);

}
